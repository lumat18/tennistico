package com.gruzini.tennistico.exceptions;

public class CourtNotFoundException extends RuntimeException {
    public CourtNotFoundException() {
        super();
    }

    public CourtNotFoundException(String message) {
        super(message);
    }

    public CourtNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }

    public CourtNotFoundException(Throwable cause) {
        super(cause);
    }

    protected CourtNotFoundException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
