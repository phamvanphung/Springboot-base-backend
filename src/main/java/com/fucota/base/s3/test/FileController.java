package com.fucota.base.s3.test;

import com.fucota.base.s3.dto.FileDto;
import com.fucota.base.s3.service.S3Service;
import io.swagger.v3.oas.annotations.parameters.RequestBody;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

@Controller
@RequiredArgsConstructor
public class FileController {

    private final S3Service service;

    @PostMapping("/upload")
    public ResponseEntity<FileDto> upload (@RequestParam MultipartFile file){
        FileDto dto = service.uploadFile(file);
        return new ResponseEntity<>(dto, HttpStatus.OK);
    }
}
