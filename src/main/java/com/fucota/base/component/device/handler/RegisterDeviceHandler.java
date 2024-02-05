package com.fucota.base.component.device.handler;

import com.fucota.base.client.middleware.MiddlewareClient;
import com.fucota.base.client.middleware.response.CheckBeneficiaryCoreResponse;
import com.fucota.base.component.device.dto.request.RegisterDeviceRequest;
import com.fucota.base.component.device.entity.Device;
import com.fucota.base.component.device.enums.DeviceErrorCode;
import com.fucota.base.component.device.service.DeviceService;
import com.fucota.base.core.RequestHandler;
import com.fucota.base.core.dto.StatusResponse;
import com.fucota.base.core.exception.BusinessException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.Objects;

@Component
@RequiredArgsConstructor
@Slf4j
public class RegisterDeviceHandler extends RequestHandler<RegisterDeviceRequest, StatusResponse> {

    private final DeviceService deviceService;
    private final MiddlewareClient middlewareClient;
    @Value("${uni-pos.bin-default}")
    private String binDefault;
    @Override
    public StatusResponse handle(RegisterDeviceRequest request) {
        try {
            Device device = deviceService.getByDeviceId(request.getDeviceId());
            if (Objects.nonNull(device)) {
                throw new BusinessException(DeviceErrorCode.DEVICE_EXISTED);
            }
            CheckBeneficiaryCoreResponse coreResponse = middlewareClient.checkBeneficiary(binDefault,request.getAccount());
            device = new Device()
                .setDeviceId(request.getDeviceId())
                .setTerminalId(request.getTid())
                .setCreatedAt(ZonedDateTime.now(ZoneId.systemDefault()))
                .setNameAccount(coreResponse.getAccountName())
                .setActualAccount(request.getAccount());
            deviceService.save(device);
            return new StatusResponse(true);
        } catch (BusinessException e) {
            throw e;
        } catch (Exception e) {
            log.error("Have error when register device :{}, with tid: {}", request.getDeviceId(), request.getTid());
            throw new BusinessException(DeviceErrorCode.DEVICE_REGISTER_FAIL);
        }
    }
}
