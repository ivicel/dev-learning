package com.mmall.common;

public enum ProductStatus {
    ON_SALE(1, "在线");

    private String value;
    private int code;

    ProductStatus(int code, String value) {
        this.code = code;
        this.value = value;
    }

    public String getValue() {
        return value;
    }

    public int getCode() {
        return code;
    }
}
