package com.fucota.base.s3.dto.request;

import com.fucota.base.core.BaseRequestData;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.Accessors;
import org.springframework.web.multipart.MultipartFile;

@Getter
@Setter
@ToString
@Accessors(chain = true)
public class UploadFileRequest extends BaseRequestData {
    private String folder;
    private MultipartFile multipartFile;
}
