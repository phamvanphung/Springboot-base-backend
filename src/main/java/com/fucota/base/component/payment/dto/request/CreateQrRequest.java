package com.fucota.base.component.payment.dto.request;

import com.fucota.base.core.BaseRequestData;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.PositiveOrZero;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class CreateQrRequest extends BaseRequestData {
    @NotBlank
    private String deviceId;
    @Positive
    private Long amount;
    private String description;
    @PositiveOrZero
    private Long timeout;
}
