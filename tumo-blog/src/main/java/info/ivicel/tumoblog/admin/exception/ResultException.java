package info.ivicel.tumoblog.admin.exception;

import info.ivicel.tumoblog.admin.enums.ResultEnums;

public class ResultException extends RuntimeException {
    private static final long serialVersionUID = 8690875171776452123L;

    public ResultException() {
        super();
    }

    public ResultException(String message) {
        super(message);
    }

    public ResultException(String message, Throwable cause) {
        super(message, cause);
    }

    public ResultException(ResultEnums result) {
        super(result.getMessage());
    }
}
