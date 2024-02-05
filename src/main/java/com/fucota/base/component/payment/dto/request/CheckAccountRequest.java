package com.fucota.base.component.payment.dto.request;

import com.fucota.base.core.BaseRequestData;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@AllArgsConstructor
public class CheckAccountRequest extends BaseRequestData {

    private String account;
}
