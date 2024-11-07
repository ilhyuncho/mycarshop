package com.carshop.mycarshop.service.fileUpload;

import com.carshop.mycarshop.controller.fileUpload.UploadResultDTO;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import net.coobird.thumbnailator.Thumbnailator;
import org.imgscalr.Scalr;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.FileSystemResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;

@Component
@RequiredArgsConstructor
@Log4j2
@Qualifier("local")
public class LocalFileService implements FileService{

    @Value("${com.cih.upload.path}")
    private String uploadPath;

    @Override
    public ResponseEntity<?> getFile(String fileName, String fileType){

        Resource resource = new FileSystemResource(uploadPath + File.separator + fileName);

        HttpHeaders headers = new HttpHeaders();
        try {
            headers.add("Content-type", Files.probeContentType(resource.getFile().toPath()));
        } catch (Exception e) {
            return ResponseEntity.internalServerError().build();
        }
        return ResponseEntity.ok().headers(headers).body(resource);
    }
    @Override
    public UploadResultDTO upload(MultipartFile image, String fileType, Boolean isResize) {

        log.error("LocalFileService!!!!!!!!!!");

        List<UploadResultDTO> listResult = new ArrayList<>();

        String originalFileName = image.getOriginalFilename();
        assert originalFileName != null;

        String uuid = UUID.randomUUID().toString();
        String formatName = originalFileName.split("\\.")[1];
        Path savePath = Paths.get(uploadPath, uuid + "_" + originalFileName);

        boolean bImage = false;
        try {
            if (isResize){
                createResizeFile(image, savePath, formatName);
            }
            else{
                createOriginalFile(image, savePath);
            }

            // 이미지 파일 이라면 ( 썸네일 생성 )
            if (Files.probeContentType(savePath).startsWith("image")) {

                createThumbnail(savePath, uuid, originalFileName);
                bImage = true;
            }

        } catch (Exception e) {
            throw new RuntimeException(e);
        }

        // 결과값 반환
        return UploadResultDTO.builder().uuid(uuid).fileName(originalFileName).img(bImage).build();
    }

//    private String uploadImage(MultipartFile image) {
//
//    }

//    public Resource getFileData(String fileName){
//
//        return new FileSystemResource(uploadPath + File.separator + fileName);
//        //        HttpHeaders headers = new HttpHeaders();
////        try {
////            headers.add("Content-type", Files.probeContentType(resource.getFile().toPath()));
////        } catch (Exception e) {
////            return ResponseEntity.internalServerError().build();
////        }
////        return ResponseEntity.ok().headers(headers).body(resource);
//
//    }


    public Optional<Map<String, Boolean>> deleteFile(String fileName, String fileType){
        Resource resource = new FileSystemResource(uploadPath + File.separator + fileName);

        return deleteFile(resource);
    }

    // 리사이즈 로 저장
    private void createResizeFile(MultipartFile multipartFile, Path savePath, String formatName) throws Exception {

        BufferedImage bi = ImageIO.read(multipartFile.getInputStream());
        bi = resizeImage(bi, 300, 300);

        ImageIO.write(bi, formatName, savePath.toFile());
    }
    private void createOriginalFile(MultipartFile multipartFile, Path savePath) throws IOException {

        multipartFile.transferTo(savePath); // 파일 저장
    }

    private void createThumbnail(Path savePath, String uuid, String originalFileName) throws IOException {

        File thumbFile = new File(uploadPath, "s_" + uuid + "_" + originalFileName);
        Thumbnailator.createThumbnail(savePath.toFile(), thumbFile, 100, 100);
    }
    // 가져온 이미지 리사이징 해주는 메서드
    private  BufferedImage resizeImage(BufferedImage originalImage, int targetWidth, int targetHeight) throws Exception {
        // resize에 들어가는 속성을 변경해서 여러 모드로 변경해줄수있다
        return Scalr.resize(originalImage, Scalr.Method.AUTOMATIC, Scalr.Mode.FIT_EXACT, targetWidth, targetHeight, Scalr.OP_ANTIALIAS);
    }

    private Optional<Map<String, Boolean>> deleteFile(Resource resource){
        // 예외를 발생시키는 대신에 Optional을 사용
        Map<String, Boolean> result = new HashMap<>();

        if(resource.exists()) {
            try {
                //throw new IOException();
                boolean delete = resource.getFile().delete();
                result.put("removed", delete);

                if (Files.probeContentType(resource.getFile().toPath()).startsWith("image")) {
                    File thumbFile = new File(uploadPath, "s_" + resource.getFilename());
                    boolean deleteThumbFile = thumbFile.delete();
                    result.put("thumbFile removed", deleteThumbFile);
                }

            } catch (IOException e) {
                result.put("removed", false);
                //return Optional.empty();      // null 이 아닌 객체를 반환
            }
        }
        else{
            result.put("removed", false);
        }
        return Optional.of(result);
    }

}
