package com.kirana.store.exceptions;

public class GeneralException extends RuntimeException {

    private Integer errorCode;

    public GeneralException(String message, Throwable cause, Integer errorCode) {
        super(message, cause);
        this.errorCode = errorCode;
    }

    public GeneralException(String message, Integer errorCode) {
        super(message);
        this.errorCode = errorCode;
    }

    public GeneralException(Throwable cause, Integer errorCode) {
        super(cause);
        this.errorCode = errorCode;
    }
}
