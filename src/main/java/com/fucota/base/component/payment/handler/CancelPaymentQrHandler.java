package com.fucota.base.component.payment.handler;

import com.fucota.base.component.payment.dto.request.CancelPaymentRequest;
import com.fucota.base.component.transaction.dto.response.TransactionInfoResponse;
import com.fucota.base.component.transaction.entity.Transaction;
import com.fucota.base.component.transaction.enums.TransactionErrorCode;
import com.fucota.base.component.transaction.enums.TransactionStatus;
import com.fucota.base.component.transaction.repository.TransactionRepository;
import com.fucota.base.core.RequestHandler;
import com.fucota.base.core.enums.CommonResponseCode;
import com.fucota.base.core.exception.BusinessException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.Objects;

@Component
@RequiredArgsConstructor
@Slf4j
public class CancelPaymentQrHandler extends RequestHandler<CancelPaymentRequest, TransactionInfoResponse> {

    private final TransactionRepository transactionRepository;

    @Override
    public TransactionInfoResponse handle(CancelPaymentRequest request) {
        try {
            Transaction transaction = transactionRepository.findById(request.getId()).orElseThrow(
                () -> new BusinessException(TransactionErrorCode.TRANSACTION_NOT_FOUND)
            );

            if (Objects.equals(transaction.getStatus(), TransactionStatus.CREATED)) {
                transaction.setStatus(TransactionStatus.CANCEL);
            }
            transaction = transactionRepository.save(transaction);
            return new TransactionInfoResponse(transaction);
        } catch (BusinessException e) {
            throw e;
        } catch (Exception e) {
            log.error("Error: {}", e.getMessage());
            throw new BusinessException(CommonResponseCode.FAILED);
        }
    }
}
