package vn.kienlongbank.base.core.exception;


import vn.kienlongbank.base.core.IResponseCode;


public class BusinessException extends RuntimeException {

    private final IResponseCode responseCode;

    public BusinessException(IResponseCode responseCode) {
        super(responseCode.getMessage());
        this.responseCode = responseCode;
    }

    public IResponseCode getResponseCode() {
        return this.responseCode;
    }

    public String getMessage() {
        return this.responseCode == null ? "unknown" : this.responseCode.getMessage();
    }

    public int getCode() {
        return this.responseCode == null ? 1 : this.responseCode.getCode();
    }
}
