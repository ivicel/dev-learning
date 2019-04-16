package com.mmall.common;

public enum ResponseCode {
    SUCCESS(0, "success"),
    ERROR(1, "error"),
    NEED_LOGIN(2, "need login"),
    ILLEGAL_ARGUMENT(3, "illegal argument"),
    PERMISSION_DENIED(4, "permission denied");


    private final int code;
    private final String message;

    ResponseCode(int code, String message) {
        this.code = code;
        this.message = message;
    }

    public int getCode() {
        return code;
    }

    public String getMessage() {
        return message;
    }
}
