package com.fucota.base.component.transaction.enums;

import lombok.Getter;

@Getter
public enum TransactionStatus {
    CREATED, SUCCESS, FAILED, TIMEOUT,CANCEL
}
