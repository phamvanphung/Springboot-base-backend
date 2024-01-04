package com.fucota.base.s3.dto.request;

import com.fucota.base.core.BaseRequestData;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class GetFileRequest extends BaseRequestData {
    private String objectName;
}
