package com.fucota.base.component.device.handler;

import com.fucota.base.component.device.dto.request.GetPageDeviceRequest;
import com.fucota.base.component.device.dto.response.DeviceResponse;
import com.fucota.base.component.device.entity.Device;
import com.fucota.base.component.device.enums.DeviceErrorCode;
import com.fucota.base.component.device.service.DeviceService;
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
public class GetPageDeviceHandler extends RequestHandler<GetPageDeviceRequest, PageResponse<DeviceResponse>> {


    private final DeviceService deviceService;

    @Override
    public PageResponse<DeviceResponse> handle(GetPageDeviceRequest request) {
        try {
            Page<Device> devicePage = deviceService.getAll(request);
            return new PageResponse<DeviceResponse>()
                .setTotalPage(devicePage.getTotalPages())
                .setPageNumber(devicePage.getNumber())
                .setPageSize(devicePage.getSize())
                .setTotalSize(devicePage.getTotalElements())
                .setItems(devicePage.stream().map(DeviceResponse::new).collect(Collectors.toList()));

        } catch (BusinessException e) {
            throw e;
        } catch (Exception e) {
            log.error("Have error : {}", e.getLocalizedMessage());
            throw new BusinessException(DeviceErrorCode.DEVICE_GET_FAIL);
        }
    }
}
