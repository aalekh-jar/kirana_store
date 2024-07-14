package com.kirana.store.exceptions;

public class CurrentConversionDataNotAvailable extends RuntimeException {
    public CurrentConversionDataNotAvailable(String message) {
        super(message);
    }

    public CurrentConversionDataNotAvailable(Throwable cause) {
        super(cause);
    }

    public CurrentConversionDataNotAvailable(String message, Throwable cause) {
        super(message, cause);
    }
}
