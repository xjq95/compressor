package me.fjnu.compressor.exception;

/**
 * 非法参数异常
 */
public class InvalidParamsException extends Exception {
    public InvalidParamsException() {
    }

    public InvalidParamsException(String message) {
        super(message);
    }

    public InvalidParamsException(String message, Throwable cause) {
        super(message, cause);
    }

    public InvalidParamsException(Throwable cause) {
        super(cause);
    }

    public InvalidParamsException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
