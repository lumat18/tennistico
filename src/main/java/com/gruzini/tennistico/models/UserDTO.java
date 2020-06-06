package com.gruzini.tennistico.models;

import com.gruzini.tennistico.annotations.StrongPassword;

import javax.validation.constraints.AssertTrue;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;

public class UserDTO {

    @NotNull
    @Email
    private String email;

    @StrongPassword
    private String password;

    private String confirmPassword;

    @AssertTrue
    public boolean isMatchingPassword() {
        return password.equals(confirmPassword);
    }
}
