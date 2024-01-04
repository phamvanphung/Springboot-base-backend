package com.fucota.base.s3.controller;

import com.fucota.base.core.ResponseBase;
import com.fucota.base.s3.dto.response.FileResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;


@Tag(name = "File Utils", description = "Module to upload and get file from s3")
@RequestMapping("/api/file")
public interface IFileController {

    @Operation(
        summary = "Upload file"
    )
    @PostMapping(value = "/v1/upload", consumes = ("multipart/form-data"))
    ResponseEntity<ResponseBase<FileResponse>> uploadFile(@RequestParam MultipartFile multipartFile);

}
