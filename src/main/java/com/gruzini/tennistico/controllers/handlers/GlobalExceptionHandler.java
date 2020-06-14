package com.gruzini.tennistico.controllers.handlers;

import com.gruzini.tennistico.messages.ErrorMessages;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(Throwable.class)
    public String handleAllUnhandledExceptions(final Throwable exception, final Model model) {
        model.addAttribute("message", ErrorMessages.ILLEGAL_ACTION_MESSAGE);
        return "error-page";
    }
}
