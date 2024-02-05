package com.fucota.base.component.device.dto.response;

import com.fucota.base.core.BaseResponseData;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.Accessors;

@Getter
@Setter
@ToString
@AllArgsConstructor
public class GetKeyDeviceResponse extends BaseResponseData {
    private String deviceId;
    private String tid;
    private String key;
}
