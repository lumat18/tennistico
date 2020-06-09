package com.gruzini.tennistico.repositories;

import com.gruzini.tennistico.domain.ActivationToken;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ActivationTokenRepository extends JpaRepository<ActivationToken, Long> {
    Optional<ActivationToken> findByValue(String tokenValue);
}
