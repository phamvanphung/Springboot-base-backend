package com.fucota.base.s3.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

@Getter
@Setter
@Accessors(chain = true)
public class FileDto {
    private String originName;
    private String objectName;
    private String url;
    private String extension;
    private String md5;
}
