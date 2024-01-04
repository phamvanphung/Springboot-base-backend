package com.fucota.base.s3.controller;

import com.fucota.base.core.BaseController;
import com.fucota.base.core.ResponseBase;
import com.fucota.base.s3.dto.request.GetFileRequest;
import com.fucota.base.s3.dto.request.UploadFileRequest;
import com.fucota.base.s3.dto.response.FileResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequiredArgsConstructor
public class FileController extends BaseController implements IFileController {

    @Override
    public ResponseEntity<ResponseBase<FileResponse>> uploadFile(MultipartFile multipartFile) {
        UploadFileRequest request = new UploadFileRequest()
            .setMultipartFile(multipartFile);
        return this.execute(request);
    }


    @Override
    public ResponseEntity<byte[]> getFile(String key) {
        GetFileRequest request = new GetFileRequest();
        request.setObjectName(key);
        ResponseEntity<ResponseBase<FileResponse>> response = this.execute(request);
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.set("Content-Disposition", "attachment; filename=\"" + response.getBody().getData().getObjectName() + "\"");
        return new ResponseEntity<>(response.getBody().getData().getContent(), httpHeaders, HttpStatus.OK);
    }
}
