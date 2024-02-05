package com.fucota.base.client.middleware.response;

import com.fucota.base.core.BaseResponseData;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CheckBeneficiaryCoreResponse extends BaseResponseData {
    private String accountName;
}
