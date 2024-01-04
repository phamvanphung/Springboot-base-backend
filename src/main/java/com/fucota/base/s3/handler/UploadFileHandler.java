package com.fucota.base.s3.handler;

import com.fucota.base.core.RequestHandler;
import com.fucota.base.core.exception.BusinessException;
import com.fucota.base.s3.dto.FileDto;
import com.fucota.base.s3.dto.request.UploadFileRequest;
import com.fucota.base.s3.dto.response.FileResponse;
import com.fucota.base.s3.enums.FileError;
import com.fucota.base.s3.service.S3Service;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class UploadFileHandler extends RequestHandler<UploadFileRequest, FileResponse> {

    private final S3Service service;

    @Override
    public FileResponse handle(UploadFileRequest request) {
        try {
            FileDto fileDto = service.uploadFile(request.getMultipartFile());
            return new FileResponse(fileDto);
        } catch (BusinessException e) {
            throw e;
        } catch (Exception e) {
            throw new BusinessException(FileError.FILE_UPLOAD_FAIL);
        }
    }
}
