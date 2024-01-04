package com.fucota.base.s3.service;

import com.fucota.base.core.exception.BusinessException;
import com.fucota.base.s3.S3ClientConfig;
import com.fucota.base.s3.dto.FileDto;
import com.fucota.base.s3.enums.FileError;
import com.fucota.base.utils.utils.FileUtil;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import software.amazon.awssdk.services.s3.model.PutObjectRequest;

import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.util.Date;
import java.util.Objects;

@Service
@RequiredArgsConstructor
public class ImpS3Service implements S3Service {

    private final S3ClientConfig s3ClientConfig;


    private String generateFileName(MultipartFile multiPart) {
        return new Date().getTime() + "-" + multiPart.getOriginalFilename().replace(" ", "_");
    }

    @Override
    @SneakyThrows
    public FileDto uploadFile(MultipartFile multipartFile) {
        if(Objects.isNull(multipartFile)){
            throw new BusinessException(FileError.FILE_NOT_FOUND);
        }
        File file = new File(multipartFile.getOriginalFilename());
        try (FileOutputStream fileOutputStream = new FileOutputStream(file)){
            fileOutputStream.write(multipartFile.getBytes());
        }
        // generate file name
        String fileName = generateFileName(multipartFile);
        // upload file
        String md5 = FileUtil.calculateMD5(file.getPath());
        if(StringUtils.isBlank(md5)){
            throw new BusinessException(FileError.FILE_MD5_CHECK_FAIL);
        }
        PutObjectRequest request =
            PutObjectRequest.builder()
            .bucket(s3ClientConfig.getBucketName())
            .key(fileName)
            .build();

        s3ClientConfig.getClient().putObject(request, file.toPath());
        FileDto fileDto = new FileDto()
            .setOriginName(multipartFile.getOriginalFilename())
            .setExtension(FileUtil.getFileExtension(multipartFile.getName()))
            .setUrl("")
            .setObjectName(fileName)
            .setMd5(md5);
        file.delete();
        return fileDto;
    }

    @Override
    public InputStream getFile(FileDto fileDto) {
        return null;
    }
}
