package com.fucota.base.component.transaction.handler;

import com.fucota.base.component.transaction.dto.request.GetDetailTransactionRequest;
import com.fucota.base.component.transaction.dto.response.TransactionDetailResponse;
import com.fucota.base.component.transaction.entity.Transaction;
import com.fucota.base.component.transaction.enums.TransactionErrorCode;
import com.fucota.base.component.transaction.repository.TransactionRepository;
import com.fucota.base.core.RequestHandler;
import com.fucota.base.core.exception.BusinessException;
import com.fucota.base.utils.utils.CommonUtils;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;


@Component
@RequiredArgsConstructor
@Slf4j
public class GetDetailTransactionHandler extends RequestHandler<GetDetailTransactionRequest, TransactionDetailResponse> {

    private final TransactionRepository transactionRepository;

    @Override
    public TransactionDetailResponse handle(GetDetailTransactionRequest request) {
        try {
            Transaction transaction = transactionRepository.findById(request.getId()).orElseThrow(
                () -> new BusinessException(TransactionErrorCode.TRANSACTION_NOT_FOUND)
            );


            return new TransactionDetailResponse(transaction);
        } catch (BusinessException e) {
            throw e;
        } catch (Exception e) {
            log.error("Have error when check transaction:");
            throw new BusinessException(TransactionErrorCode.TRANSACTION_GET_FAILED);
        }
    }
}
