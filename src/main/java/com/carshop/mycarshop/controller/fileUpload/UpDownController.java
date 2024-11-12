package com.carshop.mycarshop.controller.fileUpload;

import com.carshop.mycarshop.service.fileUpload.FileService;
import io.swagger.annotations.ApiOperation;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.*;

@RestController
@Log4j2
public class UpDownController {

    private final FileService fileService;

    @Autowired
    public UpDownController(FileService fileService) {
        this.fileService = fileService;
    }

    @ApiOperation(value = "view 파일", notes = "GET방식으로 첨부파일 조회")
    @GetMapping("/view/{fileType}/{fileName}")
    public ResponseEntity<?> viewFileData(@PathVariable String fileType,
                                          @PathVariable String fileName) throws IOException {
        return fileService.getFile(fileName, fileType);
    }

    @ApiOperation(value = "Upload Post", notes = "POST 방식으로 파일 등록")
    @PostMapping(value = "/upload", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public List<UploadResultDTO> upload(UploadFileDTO uploadFileDTO) {
        log.error("==================UploadS3 Post=====================");
        log.error(uploadFileDTO);

        List<UploadResultDTO> listResult = new ArrayList<>();

        if (uploadFileDTO.getFiles() != null) {
            uploadFileDTO.getFiles().forEach(multipartFile -> {
                try {
                    // 프로파일에 따라 upload 메서드 호출
                    listResult.add(fileService.upload(multipartFile, uploadFileDTO.getFileType(), uploadFileDTO.getIsResize()));
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            });
        }
        return listResult;
    }

    @ApiOperation(value="remove 파일", notes="Delete 방식으로 파일 삭제")
    @DeleteMapping("/remove/{fileType}/{fileName}")
    public ResponseEntity<Map<String, Boolean>> removeFileData(@PathVariable String fileType,
                                                               @PathVariable String fileName) {

        Optional<Map<String, Boolean>> result = fileService.deleteFile(fileName, fileType);

        return ResponseEntity.ok().body(result.get());
    }

//    @ApiOperation(value="remove 파일", notes="Delete 방식으로 파일 삭제")
//    @DeleteMapping("/removeS3/{fileName}")
//    public ResponseEntity<Map<String, Boolean>> deleteFileS3(@PathVariable String fileName) {
//
//        s3FileService.deleteFile(fileName);
//
//        Optional<Map<String, Boolean>> result = localFileService.deleteFile(fileName);
//
//
//        return ResponseEntity.ok().body(result.get());
//    }




//    @ApiOperation(value="remove 파일", notes="Delete 방식으로 파일 삭제")
//    @DeleteMapping("/remove/{fileName}")
//    public ResponseEntity<Map<String, Boolean>> deleteFile(@PathVariable String fileName) {
//
//        // 임시로
//        try {
//            log.error("sleep start");
//            Thread.sleep(3000); //1초 대기
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        }
//
//        Optional<Map<String, Boolean>> result = localFileService.deleteFile(fileName);
//
//        return ResponseEntity.ok().body(result.get());
//    }

}