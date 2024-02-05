package com.fucota.base.component.device.enums;

import com.fucota.base.core.IResponseCode;

public enum DeviceErrorCode implements IResponseCode {

    DEVICE_NOT_FOUND(10001,"Device not found", "Không tìm thấy thiết bị"),
    DEVICE_EXISTED(10002,"Device existed","Thiết bị đã tồn tại"),
    DEVICE_REGISTER_FAIL(10003,"Register device fail","Đăng ký thiết bị thất bại"),
    DEVICE_GET_KEY_FAIL(10004,"Device get key fail", "Yêu cầu key từ thiết bị thất bại"),
    DEVICE_GET_FAIL(10005,"Device get fail", "Lấy thông tin thiết bị thất bại"),
    DEVICE_UPDATE_FAIL(10006,"Device update failed", "Cập nhật thiết bị thất bại"),
    DEVICE_ID_EXISTED(10007,"Device id existed", "Device Id đã tồn tại trên hệ thống"),
    ;

    private  int code;
    private  String message;
    private  String viMessage;
    DeviceErrorCode(int code, String message) {
        this.code = code;
        this.message = message;
    }

    DeviceErrorCode(int code, String message, String viMessage){
        this.code = code;
        this.message = message;
        this.viMessage = viMessage;
    }


    @Override
    public int getCode() {
        return this.code;
    }

    @Override
    public String getMessage() {
        return this.message;
    }

    @Override
    public String getViMessage() {
        return this.viMessage;
    }
}
