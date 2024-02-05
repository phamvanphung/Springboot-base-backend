package com.fucota.base.component.paygate.dto.request;

import com.fucota.base.core.BaseRequestData;
import jakarta.validation.constraints.NotBlank;
import lombok.*;

@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class EncryptedBodyRequest extends BaseRequestData {

    @NotBlank
    private String data;

}
