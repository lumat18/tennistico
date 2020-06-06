package com.gruzini.tennistico.repositories;

import com.gruzini.tennistico.domain.ActivationToken;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ActivationTokenRepository extends JpaRepository<ActivationToken, Long> {
}
