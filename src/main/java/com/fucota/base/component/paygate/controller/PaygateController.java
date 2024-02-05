package com.fucota.base.component.paygate.controller;

import com.fucota.base.component.paygate.dto.request.DepositCheckingRequest;
import com.fucota.base.component.paygate.dto.request.EncryptedBodyRequest;
import com.fucota.base.component.paygate.dto.request.InquiryCheckingRequest;
import com.fucota.base.component.paygate.dto.request.NotifyTransactionRequest;
import com.fucota.base.core.BaseController;
import com.fucota.base.core.BaseRequestData;
import com.fucota.base.core.ResponseBase;
import com.fucota.base.core.ResponseData;
import com.fucota.base.core.enums.CommonResponseCode;
import com.fucota.base.core.exception.BusinessException;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;
import vn.unicloud.sdk.payment.KPayPacker;
import vn.unicloud.sdk.payment.security.PackedMessage;


@RestController
@RequiredArgsConstructor
public class PaygateController extends BaseController implements IPaygateController {

    private final KPayPacker paygatePacker;

    private <T extends BaseRequestData, I extends ResponseData> ResponseEntity<ResponseBase<String>> execute(
        String clientId,
        String signature,
        Long timestamp,
        String encryptedData,
        Class<T> requestType) {

        PackedMessage packedMessage = PackedMessage.builder()
            .clientId(clientId)
            .timestamp(timestamp)
            .signature(signature)
            .encryptedData(encryptedData)
            .build();
        T request = paygatePacker.decode(packedMessage, requestType);
        request.setClientId(clientId);
        ResponseEntity<ResponseBase<I>> response = this.execute(request);
        ResponseBase<I> body = response.getBody();
        if (body == null) {
            throw new BusinessException(CommonResponseCode.TRANSACTION_FAILED);
        }
        I data = body.getData();
        PackedMessage packedResponse = paygatePacker.encode(data);
        return this.buildResponse(packedResponse);
    }


    @Override
    public ResponseEntity<ResponseBase<String>> inquiryChecking(String clientId, String signature, Long timestamp, EncryptedBodyRequest request) {
        return this.execute(clientId, signature, timestamp, request.getData(), InquiryCheckingRequest.class);
    }

    @Override
    public ResponseEntity<ResponseBase<String>> depositChecking(String clientId, String signature, Long timestamp, EncryptedBodyRequest request) {
        return this.execute(clientId, signature, timestamp, request.getData(), DepositCheckingRequest.class);
    }

    @Override
    public ResponseEntity<ResponseBase<String>> notifyTransaction(String clientId, String signature, Long timestamp, EncryptedBodyRequest request) {
        return this.execute(clientId, signature, timestamp, request.getData(), NotifyTransactionRequest.class);
    }
}
