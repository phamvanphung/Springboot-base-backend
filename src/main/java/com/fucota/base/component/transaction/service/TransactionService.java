package com.fucota.base.component.transaction.service;

import com.fucota.base.component.paygate.enums.PaygateError;
import com.fucota.base.component.transaction.dto.request.GetPageTransactionRequest;
import com.fucota.base.component.transaction.entity.Transaction;
import com.fucota.base.component.transaction.enums.TransactionStatus;
import com.fucota.base.component.transaction.repository.TransactionRepository;
import com.fucota.base.core.exception.BusinessException;
import com.fucota.base.utils.utils.CommonUtils;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.util.Objects;

@Service
@RequiredArgsConstructor
@Slf4j
public class TransactionService {
    private final TransactionRepository transactionRepository;


    @Value("${uni-pos.paygate.prefix}")
    private String prefix;

    public Transaction save (Transaction transaction){
        return transactionRepository.save(transaction);
    }

    public Transaction findTransactionByVirtualAccount(String virtualAccount){
        return transactionRepository.findByVirtualAccount(virtualAccount).orElse(null);
    }


    public Transaction getTransactionByVirtualAccountWhenNotify(String virtualAccount) {
        String prefix = CommonUtils.getPrefixByAccount(virtualAccount);
        if (prefix == null || !prefix.equals(this.prefix)) {
            log.error("Invalid prefix: {}", prefix);
            throw new BusinessException(PaygateError.INVALID_VIRTUAL_ACCOUNT);
        }
        Transaction transaction = findTransactionByVirtualAccount(virtualAccount);
        if (transaction == null) {
            log.error("Invalid virtual account: {}", virtualAccount);
            throw new BusinessException(PaygateError.INVALID_VIRTUAL_ACCOUNT);
        }
        Long timeout = transaction.getTimeout();
        if (Objects.nonNull(timeout) &&
            timeout != 0 &&
            isTimeOut(transaction)) {
            log.error("Timeout");
            throw new BusinessException(PaygateError.TRANSACTION_TIME_OUT);
        }
        if (!TransactionStatus.CREATED.equals(transaction.getStatus()) && !TransactionStatus.CANCEL.equals(transaction.getStatus())) {
            log.error("Invalid transaction status: {}", transaction.getStatus());
            throw new BusinessException(PaygateError.INVALID_TRANSACTION_STATE);
        }
        return transaction;
    }


    public boolean isTimeOut(Transaction transaction) {
        Long timeout = transaction.getTimeout();
        if (Objects.nonNull(timeout) &&
            timeout != 0 &&
            CommonUtils.isExpired(CommonUtils.convertToMilli(transaction.getCreatedAt()) + transaction.getTimeout() * 1000L)) {
            log.error("Transaction Timeout");
            transaction.setStatus(TransactionStatus.TIMEOUT);
            transactionRepository.save(transaction);
            return true;
        }
        return false;
    }

    public Page<Transaction> getPage(GetPageTransactionRequest request) {
        return transactionRepository.findAll(request,request.getPageable());
    }
}
