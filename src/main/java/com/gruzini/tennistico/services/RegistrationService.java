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
    private final ActivationService activationService;


    public RegistrationService(UserRepository userRepository, PlayerRepository playerRepository, ActivationTokenService activationTokenService, EmailService emailService, ActivationService activationService) {
        this.userRepository = userRepository;
        this.playerRepository = playerRepository;
        this.activationService = activationService;
    }

    public void register(final User user) {
        saveUserToDatabase(user);
        activationService.sendActivationEmail(user);
    }

    private void saveUserToDatabase(final User user) {
        userRepository.save(user);
        playerRepository.save(user.getPlayer());
    }

    public void validateEmailExistence(final String email) {
        if (userRepository.findByEmail(email).isPresent()) {
            throw new EmailAlreadyExistsException("Email already exists");
        }
    }
}
