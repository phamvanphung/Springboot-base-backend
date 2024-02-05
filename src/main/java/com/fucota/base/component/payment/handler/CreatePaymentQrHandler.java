package com.fucota.base.component.payment.handler;

import com.fucota.base.component.device.entity.Device;
import com.fucota.base.component.device.enums.DeviceErrorCode;
import com.fucota.base.component.device.service.DeviceService;
import com.fucota.base.component.payment.dto.request.CreateQrRequest;
import com.fucota.base.component.payment.dto.response.CreateQrResponse;
import com.fucota.base.component.transaction.entity.Transaction;
import com.fucota.base.component.transaction.enums.TransactionErrorCode;
import com.fucota.base.component.transaction.enums.TransactionStatus;
import com.fucota.base.component.transaction.service.TransactionService;
import com.fucota.base.core.RequestHandler;
import com.fucota.base.core.exception.BusinessException;
import com.fucota.base.utils.utils.CommonUtils;
import com.fucota.base.utils.utils.VNCharacterUtils;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.Objects;

@Component
@RequiredArgsConstructor
@Slf4j
public class CreatePaymentQrHandler extends RequestHandler<CreateQrRequest, CreateQrResponse> {

    private final TransactionService transactionService;
    private final DeviceService deviceService;
    @Value("${uni-pos.paygate.prefix}")
    private String prefix;
    @Value("${uni-pos.paygate.bin}")
    private String bin;
    @Value("${uni-pos.paygate.timeout}")
    private int defaultTimeout;

    @Override
    public CreateQrResponse handle(CreateQrRequest request) {
        try {
            Device device = deviceService.getByDeviceId(request.getDeviceId());
            if (Objects.isNull(device)) {
                throw new BusinessException(DeviceErrorCode.DEVICE_NOT_FOUND);
            }

            String virtualAccount = CommonUtils.generateVirtualAccount(prefix);
            log.debug("Virtual account: {}", virtualAccount);
            String qrCodeContent = CommonUtils.generateQRCode(bin, virtualAccount, request.getAmount(), request.getDescription());
            log.debug("qrCode: {}", qrCodeContent);


            String bankAccountName = device.getNameAccount();
            String bankAccountNo = device.getActualAccount();

            if (StringUtils.isBlank(request.getDescription())) {
                String nameStoreRemoveAccent = VNCharacterUtils.removeAccent(request.getDeviceId());
                nameStoreRemoveAccent = nameStoreRemoveAccent.replaceAll("[^\\w\\s]", "");
                request.setDescription(String.format("TT tai %s so tien %s vnd", nameStoreRemoveAccent, request.getAmount()));
            }

            Transaction transaction = new Transaction()
                .setCreatedAt(ZonedDateTime.now(ZoneId.systemDefault()))
                .setStatus(TransactionStatus.CREATED)
                .setDeviceId(request.getDeviceId())
                .setTid(device.getTerminalId())
                .setAmount(request.getAmount())
                .setVirtualAccount(virtualAccount)
                .setActualAccount(bankAccountNo)
                .setDisplayName(CommonUtils.concatWithFixLength(VNCharacterUtils
                    .removeAccent(bankAccountName), 20))
                .setContent(CommonUtils.concatWithFixLength(VNCharacterUtils
                    .removeAccent(request.getDescription()), 160).replaceAll("[^\\w\\s]", ""))
                .setTimeout(((request.getTimeout() == null|| request.getTimeout() == 0) ? defaultTimeout : request.getTimeout()))
                .setToAccount(bankAccountNo)
                .setToBank(bin)
                .setBin(bin);
            transaction = transactionService.save(transaction);
            return new CreateQrResponse(transaction);

        } catch (BusinessException e) {
            throw e;
        } catch (Exception e) {
            log.error("Created QR have error in device: {}", request.getDeviceId());
            throw new BusinessException(TransactionErrorCode.TRANSACTION_CREATED_FAILED);
        }
    }
}
