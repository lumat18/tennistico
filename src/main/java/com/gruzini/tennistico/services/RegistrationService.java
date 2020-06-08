package com.gruzini.tennistico.services;

import com.gruzini.tennistico.domain.User;
import com.gruzini.tennistico.exceptions.EmailAlreadyExistsException;
import com.gruzini.tennistico.repositories.PlayerRepository;
import com.gruzini.tennistico.repositories.UserRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class RegistrationService {
    private final UserRepository userRepository;
    private final PlayerRepository playerRepository;

    public RegistrationService(UserRepository userRepository, PlayerRepository playerRepository) {
        this.userRepository = userRepository;
        this.playerRepository = playerRepository;
    }

    public void register(final User user) {
        userRepository.save(user);
        playerRepository.save(user.getPlayer());
    }

    public void validateEmailExistence(final String email) {
        if(userRepository.findByEmail(email).isPresent()){
            throw new EmailAlreadyExistsException("Email already exists");
        }
    }
}
