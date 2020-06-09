package com.gruzini.tennistico.exceptions;

public class ActivationTokenNotFoundException extends RuntimeException {
    public ActivationTokenNotFoundException() {
    }

    public ActivationTokenNotFoundException(String message) {
        super(message);
    }

    public ActivationTokenNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }

    public ActivationTokenNotFoundException(Throwable cause) {
        super(cause);
    }

    public ActivationTokenNotFoundException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
