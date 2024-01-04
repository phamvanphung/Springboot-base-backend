package com.fucota.base.s3.enums;

import com.fucota.base.core.IResponseCode;


public enum FileError implements IResponseCode {

    FILE_UPLOAD_FAIL(60001, "File upload fail", "Tải lên file thất bại"),
    FILE_MD5_CHECK_FAIL(60002,"File is incomplete", "File không toàn vẹn"),
    FILE_NOT_FOUND(60003,"File not found", "Không tìm thấy file"),
    FILE_GET_FAIL(60004,"File get fail", "Không tìm thấy file"),
    ;

    private int code;
    private String message;
    private String viMessage;

    FileError(int code, String message, String viMessage){
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
