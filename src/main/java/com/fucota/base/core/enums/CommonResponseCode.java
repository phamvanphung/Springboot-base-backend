package com.fucota.base.core.enums;


import com.fucota.base.core.IResponseCode;

public enum CommonResponseCode implements IResponseCode {
    // Common
    SUCCESS(0,"SUCCESS"),
    FAILED(1,"FAILED"),
    COMMON_ERROR(2,"COMMON_ERROR"),
    INVALID_PARAM(3,"INVALID_PARAM"),
    INVALID_SESSION(4,"INVALID_SESSION"),
    UNHANDLED_REQUEST(5,"UNHANDLED_REQUEST"),
    ACCESS_DENIED(10,"ACCESS_DENIED"),
    ;

    private  int code;
    private String name;

    CommonResponseCode(int code,String name){

        this.code = code;
        this.name = name;
    }


    @Override
    public int getCode() {
        return this.code;
    }

    @Override
    public String getName() {
        return this.name;
    }


}
