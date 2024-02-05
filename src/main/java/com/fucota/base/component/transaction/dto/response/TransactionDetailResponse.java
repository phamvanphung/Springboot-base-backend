package com.fucota.base.component.transaction.dto.response;

import com.fucota.base.component.transaction.entity.Transaction;
import com.fucota.base.component.transaction.enums.TransactionStatus;
import com.fucota.base.core.BaseResponseData;
import com.fucota.base.utils.utils.CommonUtils;
import jakarta.validation.constraints.PositiveOrZero;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.time.ZonedDateTime;

@Getter
@Setter
@ToString
public class TransactionDetailResponse extends BaseResponseData {
    private String id;
    private Long amount;
    private String description;
    @PositiveOrZero
    private Long timeout;
    private String accountName;
    private TransactionStatus status;
    private ZonedDateTime createdAt;
    private String accountNo;
    private ZonedDateTime timeCompleted;
    private String fromBank;
    private String fromAccount;
    private String deviceId;
    private String tid;

    public TransactionDetailResponse(Transaction transaction) {
        this.id = transaction.getId();
        this.amount = transaction.getAmount();
        this.description = transaction.getContent();
        this.timeout = transaction.getTimeout();
        this.accountName = transaction.getDisplayName();
        this.status = transaction.getStatus();
        this.createdAt = transaction.getCreatedAt();
        this.accountNo = transaction.getActualAccount();
        this.fromAccount = transaction.getFromAccount();
        this.fromBank = transaction.getFromBank();
        this.timeCompleted = transaction.getCompleteTime();
        this.deviceId = transaction.getDeviceId();
        this.tid = transaction.getTid();
    }
}
