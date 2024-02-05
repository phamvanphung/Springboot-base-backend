package com.fucota.base.component.transaction.enums;

import com.fucota.base.core.IResponseCode;

public enum TransactionErrorCode implements IResponseCode {

    TRANSACTION_NOT_FOUND(20001,"Transaction not found", "Không tìm thấy giao dịch"),
    TRANSACTION_CREATED_FAILED(20002,"Created transaction failed","Tạo giao dịch thất bại"),
    TRANSACTION_GET_FAILED(20003,"Get transaction failed","Không tìm thấy giao dịch"),

    ;

    private  int code;
    private  String message;
    private  String viMessage;
    TransactionErrorCode(int code, String message) {
        this.code = code;
        this.message = message;
    }

    TransactionErrorCode(int code, String message, String viMessage){
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
