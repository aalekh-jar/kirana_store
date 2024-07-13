package com.kirana.store.exceptions;

public class NoStoreRegistrationFoundException extends RuntimeException {
    public NoStoreRegistrationFoundException(String message) {
        super(message);
    }

    public NoStoreRegistrationFoundException(Throwable cause) {
        super(cause);
    }

    public NoStoreRegistrationFoundException(String message, Throwable cause) {
        super(message, cause);
    }
}
