package com.fucota.base.component.device.service;

import com.fucota.base.component.device.dto.request.GetPageDeviceRequest;
import com.fucota.base.component.device.entity.Device;
import com.fucota.base.component.device.enums.DeviceErrorCode;
import com.fucota.base.component.device.repository.DeviceRepository;
import com.fucota.base.core.exception.BusinessException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class DeviceService {
    private final DeviceRepository deviceRepository;

    public Device save(Device d) {
        return deviceRepository.save(d);
    }

    public Device getByDeviceId(String did){
        return deviceRepository.getDeviceByDeviceId(did).orElse(null);
    }


    public Page<Device> getAll(GetPageDeviceRequest request) {
        return deviceRepository.findAll(request,request.getPageable());
    }

    public Device findById(String id) {
        return deviceRepository.findById(id).orElse(null);
    }
}
