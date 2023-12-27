package vn.kienlongbank.base.core;

public interface IResponseCode {
    int code = 0;
    String message = "";
    String viMessage = "";

    int getCode();

    String getMessage();
    String getViMessage();
}
