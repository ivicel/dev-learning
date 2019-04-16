package cc.ryanc.halo.model.dto;

import cc.ryanc.halo.model.enums.ResultCode;
import java.io.Serializable;
import lombok.Data;

@Data
public class JsonResult<T> implements Serializable {

    private static final long serialVersionUID = -3466718886929029933L;

    private Integer code;
    private String msg;
    private T result;

    private JsonResult() {}

    private JsonResult(Integer code) {
        this.code = code;
    }

    private JsonResult(Integer code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    private JsonResult(Integer code, String msg, T result) {
        this.code = code;
        this.msg = msg;
        this.result = result;
    }

    public static JsonResult success() {
        return success("");
    }

    public static JsonResult success(String message) {
        return new JsonResult(ResultCode.SUCCESS.getCode(), message);
    }

    public static JsonResult fail(String message) {
        return new JsonResult(ResultCode.FAIL.getCode(), message);
    }

    public static <T> JsonResult<T> fail(T data) {
        return new JsonResult<>(ResultCode.FAIL.getCode(), "", data);
    }

    public static JsonResult fail() {
        return JsonResult.fail("");
    }

    public static <T> JsonResult<T> success(String message, T data) {
        return new JsonResult<>(ResultCode.SUCCESS.getCode(), message, data);
    }
}
