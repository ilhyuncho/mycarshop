package com.carshop.mycarshop.service.fileUpload;

import com.carshop.mycarshop.controller.fileUpload.UploadResultDTO;
import org.springframework.http.ResponseEntity;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Map;
import java.util.Optional;


public interface FileService {
    ResponseEntity<?> getFile(String fileName, String fileType) throws IOException;
    UploadResultDTO upload(MultipartFile multipartFile, String fileType, Boolean isResize) throws IOException;
    Optional<Map<String, Boolean>> deleteFile(String fileName, String fileType);
}
