package com.fucota.base.component.device.handler;


import com.fucota.base.component.device.dto.request.GetKeyDeviceRequest;
import com.fucota.base.component.device.dto.response.GetKeyDeviceResponse;
import com.fucota.base.component.device.entity.Device;
import com.fucota.base.component.device.enums.DeviceErrorCode;
import com.fucota.base.component.device.service.DeviceService;
import com.fucota.base.core.RequestHandler;
import com.fucota.base.core.exception.BusinessException;
import com.fucota.base.utils.utils.StringUtil;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.Objects;

@Component
@RequiredArgsConstructor
@Slf4j
public class GetKeyDeviceHandler extends RequestHandler<GetKeyDeviceRequest, GetKeyDeviceResponse> {

    private final DeviceService deviceService;

    @Override
    public GetKeyDeviceResponse handle(GetKeyDeviceRequest request) {
        try {
            Device device = deviceService.getByDeviceId(request.getDeviceId());
            if (Objects.isNull(device)) {
                throw new BusinessException(DeviceErrorCode.DEVICE_NOT_FOUND);
            }
            String key = StringUtil.getUUID().toUpperCase().replace("-", "");
            device.setKeyNetwork(key);
            deviceService.save(device);
            return new GetKeyDeviceResponse(device.getDeviceId(), device.getTerminalId(), device.getKeyNetwork());
        } catch (BusinessException e) {
            throw e;
        } catch (Exception e) {
            log.error("Have error when get key with device: {}", request.getDeviceId());
            throw new BusinessException(DeviceErrorCode.DEVICE_GET_KEY_FAIL);
        }
    }
}
