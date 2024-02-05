package com.fucota.base.component.transaction.handler;

import com.fucota.base.component.transaction.dto.request.GetPageTransactionRequest;
import com.fucota.base.component.transaction.dto.response.TransactionInfoResponse;
import com.fucota.base.component.transaction.entity.Transaction;
import com.fucota.base.component.transaction.enums.TransactionErrorCode;
import com.fucota.base.component.transaction.service.TransactionService;
import com.fucota.base.core.RequestHandler;
import com.fucota.base.core.dto.PageResponse;
import com.fucota.base.core.exception.BusinessException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Component;

import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
@Slf4j
public class GetPageTransactionHandler extends RequestHandler<GetPageTransactionRequest, PageResponse<TransactionInfoResponse>> {

    private final TransactionService transactionService;


    @Override
    public PageResponse<TransactionInfoResponse> handle(GetPageTransactionRequest request) {
        try {
            Page<Transaction> transactionPage = transactionService.getPage(request);
            return new PageResponse<TransactionInfoResponse>()
                .setPageSize(transactionPage.getSize())
                .setPageNumber(transactionPage.getNumber())
                .setTotalPage(transactionPage.getTotalPages())
                .setTotalSize(transactionPage.getTotalElements())
                .setItems(transactionPage.stream().map(TransactionInfoResponse::new).collect(Collectors.toList()));
        } catch (BusinessException e) {
            throw e;
        } catch (Exception e) {
            log.error("Have error when get page transaction: {}", e.getLocalizedMessage());
            throw new BusinessException(TransactionErrorCode.TRANSACTION_GET_FAILED);
        }
    }
}
