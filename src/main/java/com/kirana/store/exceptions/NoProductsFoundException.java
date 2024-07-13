package com.kirana.store.exceptions;

public class NoProductsFoundException extends RuntimeException {
    public NoProductsFoundException(String message) {
        super(message);
    }

    public NoProductsFoundException(Throwable cause) {
        super(cause);
    }

    public NoProductsFoundException(String message, Throwable cause) {
        super(message, cause);
    }
}
