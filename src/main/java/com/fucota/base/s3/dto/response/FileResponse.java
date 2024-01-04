package com.fucota.base.s3.dto.response;

import com.fucota.base.core.BaseResponseData;
import com.fucota.base.s3.dto.FileDto;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class FileResponse extends BaseResponseData {
    private String originName;
    private String objectName;
    private String url;

    public FileResponse(FileDto fileDto){
        this.originName = fileDto.getOriginName();
        this.objectName = fileDto.getObjectName();
        this.url = fileDto.getUrl();
    }
}
