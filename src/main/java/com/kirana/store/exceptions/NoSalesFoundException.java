package com.kirana.store.exceptions;

public class NoSalesFoundException extends RuntimeException{
    public NoSalesFoundException(String message) {
        super(message);
    }

    public NoSalesFoundException(Throwable cause) {
        super(cause);
    }

    public NoSalesFoundException(String message, Throwable cause) {
        super(message, cause);
    }
}
