package com.gruzini.tennistico.mappers;

import com.gruzini.tennistico.domain.User;
import com.gruzini.tennistico.domain.enums.UserStatus;
import com.gruzini.tennistico.models.forms.UserRegistrationForm;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Component
public class UserMapper {

    private final PasswordEncoder passwordEncoder;

    public UserMapper(PasswordEncoder passwordEncoder) {
        this.passwordEncoder = passwordEncoder;
    }

    public User toUser(final UserRegistrationForm registrationForm) {
        return User.builder()
                .email(registrationForm.getEmail())
                .password(passwordEncoder.encode(registrationForm.getPassword()))
                .userStatus(UserStatus.INACTIVE)
                .createdAt(LocalDateTime.now())
                .build();
    }
}
