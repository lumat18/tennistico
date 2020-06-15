package com.gruzini.tennistico.exceptions;

import com.gruzini.tennistico.messages.ErrorMessages;

public class IllegalActionException extends RuntimeException {
    public IllegalActionException() {
        super(ErrorMessages.ILLEGAL_ACTION_MESSAGE);
    }
}
