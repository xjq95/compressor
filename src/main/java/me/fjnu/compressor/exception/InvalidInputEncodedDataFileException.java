package me.fjnu.compressor.exception;

/**
 * 输入文件的编码不支持异常
 */
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
	
	public InvalidInputEncodedDataFileException(String message, Throwable cause, boolean enableSuppression,
			boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
	}
}
