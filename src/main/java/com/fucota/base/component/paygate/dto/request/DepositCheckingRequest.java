package com.fucota.base.component.paygate.dto.request;

import com.fucota.base.core.BaseRequestData;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PositiveOrZero;
import lombok.Data;

@Data
public class DepositCheckingRequest extends BaseRequestData {

    @NotEmpty
    private String virtualAccount;

    @NotNull
    @PositiveOrZero
    private Long amount;

}