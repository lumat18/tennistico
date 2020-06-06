package com.gruzini.tennistico.models;

import com.gruzini.tennistico.annotations.Email;
import com.gruzini.tennistico.annotations.StrongPassword;
import lombok.Data;

import javax.validation.constraints.AssertTrue;
import javax.validation.constraints.NotNull;

@Data
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
