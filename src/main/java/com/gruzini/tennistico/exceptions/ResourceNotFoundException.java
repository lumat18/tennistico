package com.gruzini.tennistico.exceptions;

import com.gruzini.tennistico.messages.ErrorMessages;

public class ResourceNotFoundException extends RuntimeException {
    public ResourceNotFoundException() {
        super(ErrorMessages.ILLEGAL_ACTION_MESSAGE);
    }
}
