package com.gruzini.tennistico.services;

import com.gruzini.tennistico.domain.User;
import com.gruzini.tennistico.repositories.UserRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class UserService {
    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public User getByEmail(final String email){
        return userRepository.findByEmail(email).orElseThrow(); //write custom exception
    }
}
