package info.ivicel.tumoblog.admin.enums;

public enum ResultEnums {
    SUCCESS(0, "操作成功"),
    ERROR(1, "操作失败"),

    INNER_ERROR(101, "系统发生错误"),
    INPUT_ERROR(102, "输入信息有误"),

    LOGIN_SUCCESS(200, "登录成功"),
    LOGIN_ERROR(201, "用户名或密码错误"),
    LOGIN_PASSWORD_CHECK_ERROR(202, "输入的旧密码不匹配"),

    PARAMETER_ERROR(301, "参数错误");


    private int code;
    private String message;

    ResultEnums(int code, String message) {
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
