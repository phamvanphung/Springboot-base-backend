package com.fucota.base.component.device.handler;

import com.fucota.base.client.middleware.MiddlewareClient;
import com.fucota.base.client.middleware.response.CheckBeneficiaryCoreResponse;
import com.fucota.base.component.device.dto.request.UpdateDeviceRequest;
import com.fucota.base.component.device.dto.response.DeviceResponse;
import com.fucota.base.component.device.entity.Device;
import com.fucota.base.component.device.enums.DeviceErrorCode;
import com.fucota.base.component.device.service.DeviceService;
import com.fucota.base.core.RequestHandler;
import com.fucota.base.core.exception.BusinessException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.Objects;

@Component
@RequiredArgsConstructor
@Slf4j
public class UpdateDeviceHandler extends RequestHandler<UpdateDeviceRequest, DeviceResponse> {
    private final DeviceService deviceService;
    private final MiddlewareClient middlewareClient;
    @Value("${uni-pos.bin-default}")
    private String binDefault;

    @Override
    public DeviceResponse handle(UpdateDeviceRequest request) {
        try {
            Device device = deviceService.findById(request.getId());
            if (Objects.isNull(device)) {
                throw new BusinessException(DeviceErrorCode.DEVICE_NOT_FOUND);
            }

            if (StringUtils.isNotBlank(request.getDeviceId())) {
                Device deviceFind = deviceService.getByDeviceId(request.getDeviceId());
                if (Objects.nonNull(deviceFind) && !(deviceFind.getDeviceId().equals(device.getDeviceId()))) {
                    throw new BusinessException(DeviceErrorCode.DEVICE_ID_EXISTED);
                }
                device.setDeviceId(request.getDeviceId());
            }
            if (StringUtils.isNotBlank(request.getTid())) {
                device.setTerminalId(request.getTid());
            }

            if (StringUtils.isNotBlank(request.getAccount())) {
                CheckBeneficiaryCoreResponse coreResponse = middlewareClient.checkBeneficiary(binDefault, request.getAccount());
                if (Objects.nonNull(coreResponse)) {
                    device.setNameAccount(coreResponse.getAccountName());
                    device.setActualAccount(request.getAccount());
                }
            }
            device = deviceService.save(device);
            return new DeviceResponse(device);
        } catch (BusinessException e) {
            throw e;
        } catch (Exception e) {
            throw new BusinessException(DeviceErrorCode.DEVICE_UPDATE_FAIL);
        }
    }
}
