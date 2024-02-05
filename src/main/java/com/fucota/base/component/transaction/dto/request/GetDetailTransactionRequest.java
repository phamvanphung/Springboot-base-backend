package com.fucota.base.component.transaction.dto.request;

import com.fucota.base.core.BaseRequestData;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@AllArgsConstructor
public class GetDetailTransactionRequest extends BaseRequestData {
    private String id;
}
