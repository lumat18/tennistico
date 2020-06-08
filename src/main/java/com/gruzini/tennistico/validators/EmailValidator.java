package com.gruzini.tennistico.validators;

import com.gruzini.tennistico.annotations.Email;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class EmailValidator implements ConstraintValidator<Email, String> {
   @Override
   public boolean isValid(String string, ConstraintValidatorContext context) {
      return string.matches("(^[a-zA-Z0-9_.+-]+@[a-zA-Z0-9-]+\\.[a-zA-Z0-9-.]+$)");
   }
}
