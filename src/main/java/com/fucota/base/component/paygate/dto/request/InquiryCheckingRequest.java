package com.fucota.base.component.paygate.dto.request;

import com.fucota.base.core.BaseRequestData;
import jakarta.validation.constraints.NotEmpty;
import lombok.Data;

@Data
public class InquiryCheckingRequest extends BaseRequestData {
    @NotEmpty
    private String virtualAccount;
}