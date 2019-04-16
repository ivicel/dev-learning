package info.ivicel.tumoblog.admin.dto;


import info.ivicel.tumoblog.admin.enums.ResultEnums;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ResponseResult<T> {
    private Integer code;
    private T data;

    private ResponseResult() {}

    private ResponseResult(int code, T data) {
        this.code = code;
        this.data = data;
    }

    public static <T> ResponseResult<T> of() {
        return new ResponseResult<>();
    }

    public static <T> ResponseResult<T> of(int code, T data) {
        return new ResponseResult<>(code, data);
    }

    public static ResponseResult of(ResultEnums result) {
        return new ResponseResult<>(result.getCode(), result.getMessage());
    }

    public static <T> ResponseResult<T> success(T data) {
        return ResponseResult.of(ResultEnums.SUCCESS.getCode(), data);
    }

    public static ResponseResult success() {
        return ResponseResult.of(ResultEnums.SUCCESS);
    }

    public static ResponseResult error(int code, String msg) {
        return ResponseResult.of(code, msg);
    }
}
