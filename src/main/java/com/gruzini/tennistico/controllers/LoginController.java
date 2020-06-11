package com.gruzini.tennistico.controllers;

import com.gruzini.tennistico.messages.ErrorMessages;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.InternalAuthenticationServiceException;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.WebAttributes;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import javax.servlet.http.HttpServletRequest;

@Controller
@Slf4j
public class LoginController {

    @GetMapping("/login")
    public String showLoginPage() {
        return "login";
    }

    @GetMapping("/login-error")
    //TODO try using exceptionHandler or authenticationFailureHandler - how to return view from there?
    public String showLoginErrorsOnLoginPage(final HttpServletRequest request, final Model model) {
        final AuthenticationException authenticationException = (AuthenticationException) request
                .getSession()
                .getAttribute(WebAttributes.AUTHENTICATION_EXCEPTION);
        String messageContent = "";

        if (authenticationException instanceof DisabledException) {
            messageContent = ErrorMessages.USER_NOT_ACTIVE_MESSAGE;
        }
        if (authenticationException instanceof InternalAuthenticationServiceException) {
            messageContent = ErrorMessages.BAD_CREDENTIALS_MESSAGE;
        }
        log.warn("Login error. " + messageContent);
        model.addAttribute("message", messageContent);
        return "login";
    }

}
