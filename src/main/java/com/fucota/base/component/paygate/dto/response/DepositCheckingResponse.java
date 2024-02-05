package com.fucota.base.component.paygate.dto.response;

import com.fucota.base.component.device.entity.Device;
import com.fucota.base.component.transaction.entity.Transaction;
import com.fucota.base.core.BaseResponseData;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class DepositCheckingResponse extends BaseResponseData {
    private String displayName;
    private String actualAccount;
    private String nickname;
    private Long amount;
    private boolean success;

    public DepositCheckingResponse(Device device, Transaction transaction, boolean success) {
        this.displayName = device.getNameAccount();
        this.actualAccount = device.getActualAccount();
        this.amount = transaction.getAmount();
        this.success = success;
    }
}