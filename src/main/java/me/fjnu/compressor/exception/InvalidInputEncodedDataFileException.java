package me.fjnu.compressor.exception;

public class InvalidInputEncodedDataFileException extends Exception {

    public InvalidInputEncodedDataFileException() {
    }

    public InvalidInputEncodedDataFileException(String message) {
        super(message);
    }

    public InvalidInputEncodedDataFileException(String message, Throwable cause) {
        super(message, cause);
    }

    public InvalidInputEncodedDataFileException(Throwable cause) {
        super(cause);
    }

    public InvalidInputEncodedDataFileException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
