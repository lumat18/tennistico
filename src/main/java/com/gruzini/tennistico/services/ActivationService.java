package com.gruzini.tennistico.services;

import com.gruzini.tennistico.domain.ActivationToken;
import com.gruzini.tennistico.domain.User;
import com.gruzini.tennistico.emails.MessageType;
import org.springframework.stereotype.Service;

@Service
public class ActivationService {

    private final ActivationTokenService activationTokenService;
    private final EmailService emailService;

    public ActivationService(ActivationTokenService activationTokenService, EmailService emailService) {
        this.activationTokenService = activationTokenService;
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
}
