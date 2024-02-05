package com.fucota.base.component.transaction.dto.response;

import com.fucota.base.component.transaction.entity.Transaction;
import com.fucota.base.component.transaction.enums.TransactionStatus;
import com.fucota.base.core.BaseResponseData;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.time.ZonedDateTime;

@Getter
@Setter
@ToString
public class TransactionInfoResponse extends BaseResponseData {
    private String id;
    private Long amount;
    private String accountName;
    private TransactionStatus status;
    private ZonedDateTime createdAt;
    private String deviceId;
    private String tid;

    public TransactionInfoResponse(Transaction transaction) {
        this.id = transaction.getId();
        this.amount = transaction.getAmount();
        this.accountName = transaction.getDisplayName();
        this.status = transaction.getStatus();
        this.createdAt = transaction.getCreatedAt();
        this.deviceId = transaction.getDeviceId();
        this.tid = transaction.getTid();
    }
}
