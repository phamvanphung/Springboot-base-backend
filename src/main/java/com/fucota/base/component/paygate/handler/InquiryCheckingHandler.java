package com.fucota.base.component.paygate.handler;

import com.fucota.base.component.device.entity.Device;
import com.fucota.base.component.device.service.DeviceService;
import com.fucota.base.component.paygate.dto.request.InquiryCheckingRequest;
import com.fucota.base.component.paygate.dto.response.InquiryCheckingResponse;
import com.fucota.base.component.paygate.enums.PaygateError;
import com.fucota.base.component.transaction.entity.Transaction;
import com.fucota.base.component.transaction.service.TransactionService;
import com.fucota.base.core.RequestHandler;
import com.fucota.base.core.exception.BusinessException;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Component;

import java.util.Objects;

@Component
@RequiredArgsConstructor
@Log4j2
public class InquiryCheckingHandler extends RequestHandler<InquiryCheckingRequest, InquiryCheckingResponse> {

    private final TransactionService transactionService;
    private final DeviceService deviceService;

    @Override
    @SneakyThrows
    public InquiryCheckingResponse handle(InquiryCheckingRequest request) {
        try {
            Transaction transaction = transactionService.findTransactionByVirtualAccount(request.getVirtualAccount());
            Device device = deviceService.getByDeviceId(transaction.getDeviceId());

            if (Objects.isNull(device) || !device.getActualAccount().equals(transaction.getActualAccount())) {
                throw new BusinessException(PaygateError.INVALID_VIRTUAL_ACCOUNT);
            }

            return InquiryCheckingResponse.convert(device);

        } catch (BusinessException e) {
            throw e;
        } catch (Exception e) {
            log.error(e.getMessage());
            throw new BusinessException(PaygateError.INVALID_VIRTUAL_ACCOUNT);
        }
    }
}
