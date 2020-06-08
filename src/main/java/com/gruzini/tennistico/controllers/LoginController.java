package com.gruzini.tennistico.controllers;

import lombok.extern.slf4j.Slf4j;
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

    @GetMapping("/login-error")
    public String showLoginErrorsOnLoginPage(final HttpServletRequest request, final Model model) {
        final AuthenticationException authenticationException = (AuthenticationException) request
                .getSession()
                .getAttribute(WebAttributes.AUTHENTICATION_EXCEPTION);

        log.warn("Login error. " + USER_NOT_ACTIVE_MESSAGE);
        model.addAttribute("login-error-message", USER_NOT_ACTIVE_MESSAGE);
        return "login";
    }

}
