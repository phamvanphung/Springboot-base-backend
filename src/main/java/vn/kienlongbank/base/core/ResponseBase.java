package vn.kienlongbank.base.core;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import vn.kienlongbank.base.core.enums.CommonResponseCode;

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
