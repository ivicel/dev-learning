package cc.ryanc.halo.model.enums;

public enum ResultCode {
    FAIL(0, "fail"),
    SUCCESS(1, "success");


    private Integer code;
    private String msg;

    ResultCode(Integer code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    public Integer getCode() {
        return code;
    }

    public String getMsg() {
        return msg;
    }
}
