package com.gruzini.tennistico.controllers;

import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.WebAttributes;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import javax.servlet.http.HttpServletRequest;

@Controller
@Slf4j
public class LoginController {

    public static final String USER_NOT_ACTIVE_MESSAGE = "User account is not active";
    public static final String BAD_CREDENTIALS_MESSAGE = "Email or password you entered is incorrect";

    @GetMapping("/login-error") //to do: try using exceptionHandler or authenticationFailureHandler - how to return view from there?
    public String showLoginErrorsOnLoginPage(final HttpServletRequest request, final Model model) {
        final AuthenticationException authenticationException = (AuthenticationException) request
                .getSession()
                .getAttribute(WebAttributes.AUTHENTICATION_EXCEPTION);
        String messageContent = "";

        if (authenticationException instanceof DisabledException){
            messageContent = USER_NOT_ACTIVE_MESSAGE;
        }
        if (authenticationException instanceof BadCredentialsException){
            messageContent = BAD_CREDENTIALS_MESSAGE;
        }
        log.warn("Login error. " + messageContent);
        model.addAttribute("login-error-message", messageContent);
        return "login";
    }

}
