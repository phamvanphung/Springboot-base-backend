package com.fucota.base.s3.controller;

import com.fucota.base.core.ResponseBase;
import com.fucota.base.s3.dto.response.FileResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;


@Tag(name = "File Utils", description = "Module to upload and get file from s3")
@RequestMapping("/api/file")
public interface IFileController {

    @Operation(
        summary = "Upload file"
    )
    @PostMapping(value = "/v1/upload", consumes = ("multipart/form-data"))
    ResponseEntity<ResponseBase<FileResponse>> uploadFile(@RequestParam MultipartFile multipartFile);


    @Operation(
        summary = "Get file"
    )
    @GetMapping(value = "/v1/getFile/{key}")
    ResponseEntity<byte[]> getFile(@PathVariable(name = "key") String key);

}
