package com.kirana.store.exceptions;

public class NoPurchasesFoundException extends RuntimeException {
    public NoPurchasesFoundException(String message) {
        super(message);
    }

    public NoPurchasesFoundException(String message, Throwable cause) {
        super(message, cause);
    }

    public NoPurchasesFoundException(Throwable cause) {
        super(cause);
    }
}
