package com.fucota.base.component.payment.dto.response;

import com.fucota.base.component.transaction.entity.Transaction;
import com.fucota.base.component.transaction.enums.TransactionStatus;
import com.fucota.base.core.BaseResponseData;
import com.fucota.base.utils.utils.CommonUtils;
import jakarta.validation.constraints.PositiveOrZero;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.time.ZonedDateTime;
import java.util.Objects;

@Getter
@Setter
@ToString
public class CreateQrResponse extends BaseResponseData {
    private String id;
    private Long amount;
    private String description;
    @PositiveOrZero
    private Long timeout;
    private String qrCodeContent;
    private String accountName;
    private TransactionStatus status;
    private ZonedDateTime createdAt;
    private String accountNo;

    public CreateQrResponse(Transaction transaction) {
        this.id = transaction.getId();
        this.amount = transaction.getAmount();
        this.description = transaction.getContent();
        this.timeout = transaction.getTimeout();
        this.qrCodeContent = CommonUtils.generateQRCode(transaction.getBin(), transaction.getVirtualAccount(), transaction.getAmount(), transaction.getContent());
        this.accountName = transaction.getDisplayName();
        this.status = transaction.getStatus();
        this.createdAt = transaction.getCreatedAt();
        this.accountNo = transaction.getActualAccount();
    }

}
