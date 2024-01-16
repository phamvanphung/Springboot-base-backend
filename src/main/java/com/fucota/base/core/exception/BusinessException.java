package com.fucota.base.core.exception;


import com.fucota.base.core.IResponseCode;
import com.fucota.base.core.LanguageConfigMessage;
import org.apache.commons.lang3.StringUtils;


public class BusinessException extends RuntimeException {

    private final IResponseCode responseCode;

    public BusinessException(IResponseCode responseCode) {
        super(responseCode.getName());
        this.responseCode = responseCode;
    }

    public IResponseCode getResponseCode() {
        return this.responseCode;
    }

    public String getMessage() {
        String message = LanguageConfigMessage.getMessage(this.responseCode.getName());
        return this.responseCode == null ? "unknown" : (StringUtils.isBlank(message) ? "unknown" : message);
    }

    public int getCode() {
        return this.responseCode == null ? 1 : this.responseCode.getCode();
    }
}
