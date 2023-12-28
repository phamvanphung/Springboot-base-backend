package com.fucota.base.core;

import com.fucota.base.core.enums.CommonResponseCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class ResponseBase<T> {

    private int code;
    private String message;
    private T data;

    public ResponseBase(T data) {
        this.data = data;
        this.message = CommonResponseCode.SUCCESS.getMessage();
        this.code = CommonResponseCode.SUCCESS.getCode();
    }

    public ResponseBase(int code, String message) {
        this.message = message;
        this.code = code;
    }

}
