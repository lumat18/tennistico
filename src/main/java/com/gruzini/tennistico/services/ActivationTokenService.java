package com.gruzini.tennistico.services;

import com.gruzini.tennistico.domain.ActivationToken;
import com.gruzini.tennistico.repositories.ActivationTokenRepository;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class ActivationTokenService {

    private final ActivationTokenRepository activationTokenRepository;

    public ActivationTokenService(ActivationTokenRepository activationTokenRepository) {
        this.activationTokenRepository = activationTokenRepository;
    }

    public ActivationToken createToken() {
        return ActivationToken.builder()
                .value(UUID.randomUUID().toString())
                .build();
    }

    public void saveToken(final ActivationToken token) {
        activationTokenRepository.save(token);
    }
}
