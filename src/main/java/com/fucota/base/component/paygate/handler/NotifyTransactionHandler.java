package com.fucota.base.component.paygate.handler;


import com.fucota.base.component.paygate.dto.request.NotifyTransactionRequest;
import com.fucota.base.component.paygate.dto.response.NotifyTransactionResponse;
import com.fucota.base.component.transaction.entity.Transaction;
import com.fucota.base.component.transaction.enums.TransactionStatus;
import com.fucota.base.component.transaction.service.TransactionService;
import com.fucota.base.core.RequestHandler;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import org.springframework.stereotype.Component;

import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.Objects;

@Component
@RequiredArgsConstructor
public class NotifyTransactionHandler extends RequestHandler<NotifyTransactionRequest, NotifyTransactionResponse> {

    private final TransactionService transactionService;


    @Override
    @SneakyThrows
    public NotifyTransactionResponse handle(NotifyTransactionRequest request) {
        Transaction transaction = transactionService.getTransactionByVirtualAccountWhenNotify(request.getVirtualAccount());
        transaction.setTraceId(request.getInterBankTrace());
        transaction.setTxnNumber(request.getTxnNumber());
        transaction.setFromBank(request.getFromBin());
        transaction.setFromAccount(request.getFromAccount());
        transaction.setVirtualAccount(request.getVirtualAccount());
        transaction.setActualAccount(request.getActualAccount());
        transaction.setCompleteTime(ZonedDateTime.now(ZoneId.systemDefault()));

        if (request.isSuccess()
            && Objects.equals(request.getAmount(), transaction.getAmount())
            && transaction.getStatus().equals(TransactionStatus.CREATED)
        ) {
            transaction.setStatus(TransactionStatus.SUCCESS);
        } else {
            transaction.setStatus(TransactionStatus.FAILED);
        }
        transaction = transactionService.save(transaction);
        return new NotifyTransactionResponse(true);
    }
}