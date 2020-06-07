package com.gruzini.tennistico.models.forms;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.gruzini.tennistico.annotations.Email;
import com.gruzini.tennistico.annotations.StrongPassword;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.AssertTrue;
import javax.validation.constraints.NotNull;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UserRegistrationForm {

    @NotNull
    @Email
    private String email;

    @NotNull
    @StrongPassword
    private String password;

    private String confirmedPassword;

    @AssertTrue(message = "Passwords do not match.")
    @JsonIgnore
    public boolean isPasswordMatching(){
        if(password == null || confirmedPassword == null){
            return false;
        }
        return password.equals(confirmedPassword);
    }
}
