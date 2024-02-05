package com.fucota.base.component.payment.handler;

import com.fucota.base.client.middleware.MiddlewareClient;
import com.fucota.base.client.middleware.response.CheckBeneficiaryCoreResponse;
import com.fucota.base.component.payment.dto.request.CheckAccountRequest;
import com.fucota.base.component.payment.dto.response.CheckAccountResponse;
import com.fucota.base.core.RequestHandler;
import com.fucota.base.core.enums.CommonResponseCode;
import com.fucota.base.core.exception.BusinessException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
@Slf4j
public class CheckAccountHandler extends RequestHandler<CheckAccountRequest, CheckAccountResponse> {


    private final MiddlewareClient middlewareClient;
    @Value("${uni-pos.bin-default}")
    private String binDefault;


    @Override
    public CheckAccountResponse handle(CheckAccountRequest request) {
        try {
            CheckBeneficiaryCoreResponse response = middlewareClient.checkBeneficiary(binDefault, request.getAccount());
            log.debug("Get account info response: {}", response);
            return new CheckAccountResponse(request.getAccount(), response.getAccountName());
        } catch (BusinessException e) {
            throw e;
        } catch (Exception e) {
            log.error("Error: {}", e.getMessage());
            throw new BusinessException(CommonResponseCode.THIRD_PARTY_API_ERROR);
        }
    }
}
