package com.fucota.base.component.device.controller;

import com.fucota.base.component.device.dto.request.GetKeyDeviceRequest;
import com.fucota.base.component.device.dto.request.GetPageDeviceRequest;
import com.fucota.base.component.device.dto.request.RegisterDeviceRequest;
import com.fucota.base.component.device.dto.request.UpdateDeviceRequest;
import com.fucota.base.component.device.dto.response.DeviceResponse;
import com.fucota.base.component.device.dto.response.GetKeyDeviceResponse;
import com.fucota.base.core.BaseController;
import com.fucota.base.core.ResponseBase;
import com.fucota.base.core.dto.PageResponse;
import com.fucota.base.core.dto.StatusResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class DeviceController extends BaseController implements IDeviceController {

    @Override
    public ResponseEntity<ResponseBase<StatusResponse>> registerDevice(RegisterDeviceRequest request) {
        return this.execute(request);
    }

    @Override
    public ResponseEntity<ResponseBase<GetKeyDeviceResponse>> getKeyDevice(GetKeyDeviceRequest request) {
        return this.execute(request);
    }


    @Override
    public ResponseEntity<ResponseBase<PageResponse<DeviceResponse>>> getAllPage(GetPageDeviceRequest request) {
        return this.execute(request);
    }

    @Override
    public ResponseEntity<ResponseBase<DeviceResponse>> updateDevice(String id, UpdateDeviceRequest request) {
        request.setId(id);
        return this.execute(request);
    }
}
