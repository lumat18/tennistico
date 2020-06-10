package com.gruzini.tennistico.services;

import com.gruzini.tennistico.domain.ActivationToken;
import com.gruzini.tennistico.domain.User;
import com.gruzini.tennistico.emails.MessageType;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class ActivationService {

    private final ActivationTokenService activationTokenService;
    private final UserService userService;
    private final EmailService emailService;

    public ActivationService(ActivationTokenService activationTokenService, UserService userService, EmailService emailService) {
        this.activationTokenService = activationTokenService;
        this.userService = userService;
        this.emailService = emailService;
    }

    public void sendActivationEmail(final User user) {
        final ActivationToken activationToken = createActivationToken(user);
        emailService.sendEmail(user.getEmail(), activationToken.getValue(), MessageType.ACTIVATION);
    }

    private ActivationToken createActivationToken(final User user) {
        final ActivationToken token = activationTokenService.createToken();
        token.setUser(user);
        activationTokenService.saveToken(token);
        return token;
    }

    public void activate(final User user) {
        userService.activateUser(user);
        log.info("User account " + user.getEmail() + " was activated");
    }
}
