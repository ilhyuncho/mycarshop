package com.carshop.mycarshop.service.fileUpload.aws;

import com.amazonaws.AmazonServiceException;
import com.amazonaws.SdkClientException;
import com.amazonaws.services.s3.AmazonS3Client;
import com.amazonaws.services.s3.model.*;
import com.amazonaws.util.IOUtils;
import com.carshop.mycarshop.common.exception.aws.AwsExceptions;
import com.carshop.mycarshop.controller.fileUpload.UploadResultDTO;
import com.carshop.mycarshop.service.fileUpload.FileService;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import net.coobird.thumbnailator.Thumbnails;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.*;
import java.net.URLEncoder;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;

@Service
@RequiredArgsConstructor
@Log4j2
@ConditionalOnProperty(name="file.upload.s3", matchIfMissing = false)
public class S3FileService implements FileService {

    private final AmazonS3Client amazonS3Client;

    @Value("${cloud.aws.s3.bucket}")
    private String bucketName;

    @Override
    public ResponseEntity<byte[]> getFile(String fileName, String dirName) throws IOException { // 객체 다운  filePath : 폴더명/파일네임.파일확장자

        String filePath = dirName + "/"+  fileName;

        S3Object s3Object = amazonS3Client.getObject(new GetObjectRequest(bucketName, filePath));
        S3ObjectInputStream objectInputStream = s3Object.getObjectContent();
        byte[] bytes = IOUtils.toByteArray(objectInputStream);

        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.setContentType(contentType(filePath));
        httpHeaders.setContentLength(bytes.length);
        String[] arr = filePath.split("/");
        String type = arr[arr.length - 1];
        String fileName1 = URLEncoder.encode(type, "UTF-8").replaceAll("\\+", "%20");
        httpHeaders.setContentDispositionFormData("attachment", fileName1); // 파일이름 지정
        //log.error("getFileData: fileName1 : " + fileName1);
        return new ResponseEntity<>(bytes, httpHeaders, HttpStatus.OK);
    }
    @Override
    public UploadResultDTO upload(MultipartFile multipartFile, String fileType, Boolean isResize) throws IOException {

        String uuid = UUID.randomUUID().toString().substring(0, 13);

        File uploadFile = convertToFile(multipartFile)
                .orElseThrow(AwsExceptions.FILE_CONVERT_FAIL::get);

        UploadResultDTO uploadResultDTO = uploadS3(uploadFile, fileType, uuid);

        // 파일 종류 체크( jpg, png, .. )
        Path savePath = Paths.get(uploadFile.getPath());
        if (Files.probeContentType(savePath).startsWith("image")) {
            // 이미지 파일 이라면 ( 썸네일 생성 )
            uploadThumbnail(multipartFile, fileType, uuid);
            uploadResultDTO.setImg(true);
        }

        return uploadResultDTO;
    }
    @Override
    public Optional<Map<String, Boolean>> deleteFile(String fileName, String fileType) { // filePath : 폴더명/파일네임.파일확장자

        Map<String, Boolean> result = new HashMap<>();

        String filePath = fileType + "/" + fileName;

        try {
            // S3에서 삭제
            amazonS3Client.deleteObject(new DeleteObjectRequest(bucketName, filePath));
            result.put("removed", true);

            MediaType mediaType = contentType(filePath);
            // 썸네일 파일 삭제
            if (mediaType == MediaType.IMAGE_PNG
                    || mediaType == MediaType.IMAGE_JPEG) {

                filePath = fileType + "/" + "s_" + fileName;

                log.error("deleteFile thumbFile: " + filePath);

                amazonS3Client.deleteObject(new DeleteObjectRequest(bucketName, filePath));
                result.put("thumbFile removed", true);
            }
        } catch (AmazonServiceException e) {
            e.printStackTrace();
        } catch (SdkClientException e) {
            e.printStackTrace();
        }

        return Optional.of(result);
    }
    private void uploadThumbnail(MultipartFile multipartFile, String dirName, String uuid) throws IOException {
        String uploadPathName = dirName + "/" + "s_" + uuid + "_" + multipartFile.getOriginalFilename();

        File file = convertToThumbnail(multipartFile);
        String thumbnailUrl = putS3(file,uploadPathName );
    }

    private UploadResultDTO uploadS3(File uploadFile, String dirName, String uuid){

        String uploadPathName = dirName + "/" + uuid + "_" + uploadFile.getName();

        String uploadImageUrl = putS3(uploadFile, uploadPathName);

        // convert() 과정에서 로컬에 생성된 파일 삭제
        removeLocalTempFile(uploadFile);

        return UploadResultDTO.builder()
                .uuid(uuid)
                .fileName(uploadFile.getName())
                .img(true)
                .build();
    }

    private Optional<File> convertToFile(MultipartFile multipartFile) throws IOException{
        // 기존 파일 이름으로 새로운 File 객체 생성
        // 해당 객체는 프로그램이 실행되는 로컬 디렉토리(루트 디렉토리)에 위치하게 됨
        File convertFile = new File(Objects.requireNonNull(multipartFile.getOriginalFilename()));

        if (convertFile.createNewFile()){ // 해당 경로에 파일이 없을 경우, 새 파일 생성
            try (FileOutputStream fos = new FileOutputStream(convertFile)) {
                // multipartFile의 내용을 byte로 가져와서 write
                fos.write(multipartFile.getBytes());
            }
            return Optional.of(convertFile);
        }
        return Optional.empty();
    }

    private File convertToThumbnail(MultipartFile multipartFile) throws IOException {

        String originalFilename = multipartFile.getOriginalFilename();

        File file = new File("s_" + originalFilename);

        Thumbnails.of(multipartFile.getInputStream())
                .size(100, 100) // 변환 시 사이즈
                .outputQuality(0.9) // 변환 시 퀄리티
                .toFile(file);
        return file;
    }

    private String putS3(File uploadFile, String uploadPathName){

        amazonS3Client.putObject(
                new PutObjectRequest(bucketName, uploadPathName, uploadFile) // PublicRead 권한으로 upload
        );

        return amazonS3Client.getUrl(bucketName, uploadPathName).toString(); // File 의 URL return
    }

    private MediaType contentType(String keyname) {
        String[] arr = keyname.split("\\.");
        String type = arr[arr.length - 1];
        return switch (type) {
            case "txt" -> MediaType.TEXT_PLAIN;
            case "png" -> MediaType.IMAGE_PNG;
            case "jpg" -> MediaType.IMAGE_JPEG;
            default -> MediaType.APPLICATION_OCTET_STREAM;
        };
    }

    private void removeLocalTempFile(File targetFile){
        String fileName = targetFile.getName();
        // convertToFile() 과정에서 로컬에 생성된 파일을 삭제
        if (targetFile.delete()){
            log.error(fileName + "파일 삭제 완료");
        } else {
            log.error(fileName + "파일 삭제 실패");
        }
    }

//    private String getKeyFromImageAddress(String imageAddress){
//        log.error("getKeyFromImageAddress() imageAddress :" + imageAddress);
//
//        try{
//            URL url = new URL(imageAddress);
//            String decodingKey = URLDecoder.decode(url.getPath(), "UTF-8");
//            return decodingKey.substring(1); // 맨 앞의 '/' 제거
//        }catch (MalformedURLException | UnsupportedEncodingException e){
////            throw new S3Exception(ErrorCode.IO_EXCEPTION_ON_IMAGE_DELETE);
//            throw new S3FileNullException("업로드 하려는 파일이 비어 있습니다");
//        }
//    }

    //    @Override
//    public Optional<Map<String, Boolean>> deleteFile(String fileName){
//
//        String imageAddress = "shopImage/" + fileName;
//
//        String key = getKeyFromImageAddress(imageAddress);
//        try{
//            log.error("deleteImageFromS3() key : " + key);
//
//            amazonS3Client.deleteObject(new DeleteObjectRequest(bucketName, key));
//        }catch (Exception e){
////            throw new S3Exception(ErrorCode.IO_EXCEPTION_ON_IMAGE_DELETE);
//            throw new S3FileNullException("업로드 하려는 파일이 비어 있습니다");
//        }
//
//        return null;
//    }





}
