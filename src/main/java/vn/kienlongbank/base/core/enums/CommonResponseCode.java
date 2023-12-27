package vn.kienlongbank.base.core.enums;


import vn.kienlongbank.base.core.IResponseCode;

public enum CommonResponseCode implements IResponseCode {
    // Common
    SUCCESS(0, "Success", "Thao tác thành công"),
    FAILED(1, "Failed", "Thao tác thất bại"),
    COMMON_ERROR(2, "Common Error", "Có lỗi xảy ra"),
    INVALID_PARAM(3, "Invalid param", "Thông số không hợp lệ"),
    INVALID_SESSION(4, "Invalid session", "Phiên làm việc hết hạn"),
    UNHANDLED_REQUEST(5, "Unknown handler to handle this request","Chưa có quá trình xử lý cho yêu cầu này"),
    ACCESS_DENIED(10, "Access denied","Từ chối truy cập"),

    ;

    private  int code;
    private  String message;
    private  String viMessage;
    CommonResponseCode(int code, String message) {
        this.code = code;
        this.message = message;
    }

    CommonResponseCode(int code, String message, String viMessage){
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
