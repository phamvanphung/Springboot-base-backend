package com.fucota.base.component.paygate.dto.request;

import com.fucota.base.core.BaseRequestData;
import lombok.Data;

@Data
public class NotifyTransactionRequest extends BaseRequestData {

    private boolean success;

    private String interBankTrace;

    private String transactionId;

    private String virtualAccount;

    private String actualAccount;

    private String fromBin;

    private String fromAccount;

    private Long amount;

    private String statusCode;

    private String txnNumber;

    private String transferDesc;

    private String time;

}