package com.fucota.base.s3.handler;

import com.fucota.base.core.RequestHandler;
import com.fucota.base.core.exception.BusinessException;
import com.fucota.base.s3.dto.FileDto;
import com.fucota.base.s3.dto.request.GetFileRequest;
import com.fucota.base.s3.dto.response.FileResponse;
import com.fucota.base.s3.enums.FileError;
import com.fucota.base.s3.service.S3Service;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class GetFileHandler extends RequestHandler<GetFileRequest, FileResponse> {

    private final S3Service service;

    @Override
    public FileResponse handle(GetFileRequest request) {
        try {
            FileDto fileDto = new FileDto();
            fileDto.setObjectName(request.getObjectName());
            byte[] bytes = service.getFile(fileDto);
            return new FileResponse().setContent(bytes).setObjectName(request.getObjectName());
        } catch (BusinessException e) {
            throw e;
        } catch (Exception e) {
            throw new BusinessException(FileError.FILE_GET_FAIL);
        }
    }
}
