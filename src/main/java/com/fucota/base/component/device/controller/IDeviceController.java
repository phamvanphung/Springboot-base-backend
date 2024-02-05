package com.fucota.base.component.device.controller;


import com.fucota.base.component.device.dto.request.GetKeyDeviceRequest;
import com.fucota.base.component.device.dto.request.GetPageDeviceRequest;
import com.fucota.base.component.device.dto.request.RegisterDeviceRequest;
import com.fucota.base.component.device.dto.request.UpdateDeviceRequest;
import com.fucota.base.component.device.dto.response.DeviceResponse;
import com.fucota.base.component.device.dto.response.GetKeyDeviceResponse;
import com.fucota.base.core.ResponseBase;
import com.fucota.base.core.dto.PageResponse;
import com.fucota.base.core.dto.StatusResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springdoc.core.annotations.ParameterObject;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@Tag(name = "Device Controller")
@RequestMapping("/device/v1")
public interface IDeviceController {

    @Operation(
        summary = "Dang ky thiet bi"
    )
    @PostMapping("/register")
    ResponseEntity<ResponseBase<StatusResponse>> registerDevice(@Validated @RequestBody RegisterDeviceRequest request);


    @Operation(
        summary = "Lay key"
    )
    @PostMapping("/key")
    ResponseEntity<ResponseBase<GetKeyDeviceResponse>> getKeyDevice(@Validated @RequestBody GetKeyDeviceRequest request);


    @Operation(
        summary = "Lay danh sach thiet bi"
    )
    @GetMapping("/all")
    ResponseEntity<ResponseBase<PageResponse<DeviceResponse>>> getAllPage(@ParameterObject GetPageDeviceRequest request);



    @Operation(
        summary = "Update device"
    )
    @PutMapping("/{id}")
    ResponseEntity<ResponseBase<DeviceResponse>> updateDevice(@PathVariable(name = "id") String id, UpdateDeviceRequest request);
}
