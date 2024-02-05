package com.fucota.base.component.paygate.dto.response;

import com.fucota.base.component.device.entity.Device;
import com.fucota.base.core.BaseResponseData;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@AllArgsConstructor
@Builder
public class InquiryCheckingResponse extends BaseResponseData {
    private String actualAccount;
    private String displayName;

    public static InquiryCheckingResponse convert(Device device) {
        return InquiryCheckingResponse.builder()
            .actualAccount(device.getActualAccount())
            .displayName(device.getNameAccount())
            .build();
    }
}
