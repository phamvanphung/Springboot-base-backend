package com.fucota.base.s3.service;

import com.fucota.base.s3.dto.FileDto;
import org.springframework.web.multipart.MultipartFile;

import java.io.InputStream;


public interface S3Service {

    FileDto uploadFile(MultipartFile file);
    byte[] getFile(FileDto fileDto);

}
