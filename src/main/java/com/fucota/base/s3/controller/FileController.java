package com.fucota.base.s3.controller;

import com.fucota.base.core.BaseController;
import com.fucota.base.core.ResponseBase;
import com.fucota.base.s3.dto.FileDto;
import com.fucota.base.s3.dto.request.UploadFileRequest;
import com.fucota.base.s3.dto.response.FileResponse;
import com.fucota.base.s3.service.S3Service;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@RestController
public class FileController extends BaseController implements IFileController {

    @Override
    public ResponseEntity<ResponseBase<FileResponse>> uploadFile(MultipartFile multipartFile) {
        UploadFileRequest request = new UploadFileRequest()
            .setMultipartFile(multipartFile);
        return this.execute(request);
    }
}
