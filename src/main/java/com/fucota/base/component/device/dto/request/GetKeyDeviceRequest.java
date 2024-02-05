package com.fucota.base.component.device.dto.request;

import com.fucota.base.core.BaseRequestData;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class GetKeyDeviceRequest extends BaseRequestData {
    @NotBlank
    private String deviceId;
}
