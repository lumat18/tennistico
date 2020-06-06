package com.gruzini.tennistico.mappers;

import com.gruzini.tennistico.domain.User;
import com.gruzini.tennistico.enums.UserStatus;
import com.gruzini.tennistico.models.UserDTO;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Component
public class UserMapper {
//    private PasswordEncoder passwordEncoder;
//
//    public UserMapper(PasswordEncoder passwordEncoder) {
//        this.passwordEncoder = passwordEncoder;
//    }

    public User mapUserDTOtoUser(final UserDTO userDTO){
        return User.builder()
                .email(userDTO.getEmail())
                .password(userDTO.getPassword()) //TO DO: encoder
                .createdAt(LocalDateTime.now())
                .userStatus(UserStatus.INACTIVE)
                .build();
    }
}
