package com.fucota.base.component.paygate.enums;

import com.fucota.base.core.IResponseCode;

public enum PaygateError  implements IResponseCode {

    // Paygate
    INVALID_VIRTUAL_ACCOUNT(403, "Invalid virtual account", "Tài khoản ảo không hợp lệ"),
    INVALID_AMOUNT(404, "Invalid amount", "Số tiền không hợp lệ"),
    TRANSACTION_TIME_OUT(401, "Transaction timeout", "Giao dịch hết hạn"),
    INVALID_TRANSACTION_STATE(405, "Invalid transaction state", "Trạng thái giao dịch không hợp lệ"),

    ;

    private  int code;
    private  String message;
    private  String viMessage;
    PaygateError(int code, String message) {
        this.code = code;
        this.message = message;
    }

    PaygateError(int code, String message, String viMessage){
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

