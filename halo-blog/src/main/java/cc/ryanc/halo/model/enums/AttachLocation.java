package cc.ryanc.halo.model.enums;

public enum AttachLocation {
    SERVER(0, "server"),
    QINIU(1, "qiniu"),
    UPYUN(2, "upyun");

    private Integer code;
    private String desc;

    AttachLocation(Integer code, String desc) {
        this.code = code;
        this.desc = desc;
    }

    public Integer getCode() {
        return code;
    }

    public String getDesc() {
        return desc;
    }
}
