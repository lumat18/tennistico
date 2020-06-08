package com.gruzini.tennistico.controllers.handlers;

import com.gruzini.tennistico.exceptions.EmailAlreadyExistsException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@ControllerAdvice
public class RegistrationControllerExceptionHandler {
   @ExceptionHandler(EmailAlreadyExistsException.class)
   public String handleExistingEmailException(final EmailAlreadyExistsException exp,
                                              final RedirectAttributes attributes) {
      attributes.addFlashAttribute("exp", exp.getMessage());
      return "redirect:/registration/step-one";
   }
}
