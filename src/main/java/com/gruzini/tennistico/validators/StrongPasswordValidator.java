package com.gruzini.tennistico.validators;

import com.gruzini.tennistico.annotations.StrongPassword;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class StrongPasswordValidator implements ConstraintValidator<StrongPassword, String> {
    @Override
    public boolean isValid(String string, ConstraintValidatorContext context) {
        return string.matches("^(?=.*[0-9])(?=.*[a-zA-Z])(?=\\S+$).{5,15}$");
    }
}
