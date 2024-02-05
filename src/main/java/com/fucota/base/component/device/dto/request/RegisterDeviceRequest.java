package com.fucota.base.component.device.dto.request;

import com.fucota.base.core.BaseRequestData;
import com.fucota.base.core.RequestData;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class RegisterDeviceRequest extends BaseRequestData {
    @NotBlank
    private String tid;
    @NotBlank
    private String deviceId;
    @NotBlank
    private String account;
}
