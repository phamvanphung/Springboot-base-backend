package com.fucota.base.component.transaction.entity;


import com.fucota.base.component.device.entity.Device;
import com.fucota.base.component.transaction.enums.TransactionStatus;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;
import lombok.experimental.FieldNameConstants;

import java.time.ZonedDateTime;

@Entity
@Table(name = "transaction")
@Getter
@Setter
@Accessors(chain = true)
@FieldNameConstants
public class Transaction {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;

    private String tid;
    private String deviceId;
    private String actualAccount;

    private Long amount;
    @Enumerated(EnumType.STRING)
    private TransactionStatus status;
    private String content;
    private Long timeout;
    private String toAccount;
    private String fromAccount;
    private String toBank;
    private String fromBank;
    private String bankRefTransId;
    private String virtualAccount;
    private String bin;
    private ZonedDateTime completeTime;
    private String displayName;
    private String urlSuccess;
    private String urlFail;
    private String traceId;
    private String txnNumber;
    private ZonedDateTime createdAt;
    private String inVoiceCode;
}
