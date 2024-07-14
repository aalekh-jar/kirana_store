package com.kirana.store.exceptions;

public class DataValidationError extends RuntimeException {

    public DataValidationError(String message) {
        super(message);
    }

    public DataValidationError(String message, Throwable cause) {
        super(message, cause);
    }

    public DataValidationError(Throwable cause) {
        super(cause);
    }

}
