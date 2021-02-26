package dckj.arrange.common.exception;

/**
 * @Classname CheckedException
 * @Description TODO
 * @Date 2019/8/7 16:57
 * @Created by JinPeng
 * @Version 1.0
 */
public class CheckedException extends RuntimeException {

    private static final long serialVersionUID = 1L;

    private String errorMsg ;

    public CheckedException() {
    }

    public CheckedException(String message) {
        super(message);
        this.errorMsg = message;
    }

    public CheckedException(Throwable cause) {
        super(cause);
    }

    public CheckedException(String message, Throwable cause) {
        super(message, cause);
    }

    public CheckedException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }

}