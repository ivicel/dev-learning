package cc.ryanc.halo.exception;

public class PageNotFoundException extends RuntimeException {

    private static final long serialVersionUID = 2626861378306317615L;

    public PageNotFoundException() {
        this("找不到页面");
    }

    public PageNotFoundException(String message) {
        super(message);
    }

    public PageNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }

    public PageNotFoundException(Throwable cause) {
        super(cause);
    }
}
