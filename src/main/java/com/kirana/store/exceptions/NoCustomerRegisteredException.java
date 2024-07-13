package com.kirana.store.exceptions;

public class NoCustomerRegisteredException extends RuntimeException {
    public NoCustomerRegisteredException(String message) {
        super(message);
    }

    public NoCustomerRegisteredException(String message, Throwable cause) {
        super(message, cause);
    }

    public NoCustomerRegisteredException(Throwable cause) {
        super(cause);
    }
}
