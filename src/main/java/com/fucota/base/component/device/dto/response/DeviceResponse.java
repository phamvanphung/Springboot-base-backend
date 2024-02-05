package com.fucota.base.component.device.dto.response;

import com.fucota.base.component.device.entity.Device;
import com.fucota.base.core.BaseResponseData;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.time.ZonedDateTime;

@Getter
@Setter
@ToString
public class DeviceResponse extends BaseResponseData {

    private String id;
    private String deviceId;
    private String terminalId;
    private String actualAccount;
    private String keyNetwork;
    private String nameAccount;
    private ZonedDateTime createdAt;

    public DeviceResponse(Device device) {
        this.id = device.getId();
        this.deviceId = device.getDeviceId();
        this.terminalId = device.getTerminalId();
        this.actualAccount = device.getActualAccount();
        this.keyNetwork = device.getKeyNetwork();
        this.nameAccount = device.getNameAccount();
        this.createdAt = device.getCreatedAt();
    }
}
