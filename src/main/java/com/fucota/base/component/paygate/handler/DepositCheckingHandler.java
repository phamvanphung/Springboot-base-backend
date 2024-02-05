package com.fucota.base.component.paygate.handler;


import com.fucota.base.component.device.entity.Device;
import com.fucota.base.component.device.service.DeviceService;
import com.fucota.base.component.paygate.dto.request.DepositCheckingRequest;
import com.fucota.base.component.paygate.dto.response.DepositCheckingResponse;
import com.fucota.base.component.paygate.enums.PaygateError;
import com.fucota.base.component.transaction.entity.Transaction;
import com.fucota.base.component.transaction.service.TransactionService;
import com.fucota.base.core.RequestHandler;
import com.fucota.base.core.exception.BusinessException;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import org.springframework.stereotype.Component;

import java.util.Objects;

@Component
@RequiredArgsConstructor
public class DepositCheckingHandler extends RequestHandler<DepositCheckingRequest, DepositCheckingResponse> {

    private final TransactionService transactionService;
    private final DeviceService deviceService;

    @Override
    @SneakyThrows
    public DepositCheckingResponse handle(DepositCheckingRequest request) {
        Transaction transaction = transactionService.findTransactionByVirtualAccount(request.getVirtualAccount());
        if (!Objects.equals(transaction.getAmount(), request.getAmount())) {
            throw new BusinessException(PaygateError.INVALID_AMOUNT);
        }

        Device device = deviceService.getByDeviceId(transaction.getDeviceId());
        if (Objects.isNull(device) || !device.getActualAccount().equals(transaction.getActualAccount())) {
            throw new BusinessException(PaygateError.INVALID_VIRTUAL_ACCOUNT);
        }

        return new DepositCheckingResponse(device, transaction, true);
    }
}