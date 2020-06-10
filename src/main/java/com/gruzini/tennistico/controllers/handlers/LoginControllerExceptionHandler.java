package com.gruzini.tennistico.controllers.handlers;

import com.gruzini.tennistico.exceptions.LoginCredentialsInvalidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@ControllerAdvice
public class LoginControllerExceptionHandler {
   @ExceptionHandler(LoginCredentialsInvalidException.class)
   public String handleExistingEmailException(final LoginCredentialsInvalidException exp,
                                              final RedirectAttributes attributes) {
      attributes.addFlashAttribute("exp", exp.getMessage());
      return "redirect:/login";
   }
}
