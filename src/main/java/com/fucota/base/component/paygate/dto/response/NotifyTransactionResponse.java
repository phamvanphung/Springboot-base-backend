package com.fucota.base.component.paygate.dto.response;

import com.fucota.base.core.BaseResponseData;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class NotifyTransactionResponse extends BaseResponseData {
    private boolean status;
}
