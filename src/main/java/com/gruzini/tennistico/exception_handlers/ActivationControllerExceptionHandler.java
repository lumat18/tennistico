package com.gruzini.tennistico.exception_handlers;

import com.gruzini.tennistico.exceptions.ActivationTokenNotFoundException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
@Slf4j
public class ActivationControllerExceptionHandler {
    private static final String ACCOUNT_NOT_ACTIVATED = "Failed to activate account";

    @ExceptionHandler(ActivationTokenNotFoundException.class)
    public String handleException(final Model model) {
        model.addAttribute("message", ACCOUNT_NOT_ACTIVATED);
        log.warn("Activation token could not be found in database. Failed to activate the account");
        return "login";
    }
}
