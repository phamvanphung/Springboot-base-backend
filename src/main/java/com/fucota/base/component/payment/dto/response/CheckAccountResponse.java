package com.fucota.base.component.payment.dto.response;

import com.fucota.base.core.BaseResponseData;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@AllArgsConstructor
public class CheckAccountResponse extends BaseResponseData {
    private String account;
    private String name;
}
