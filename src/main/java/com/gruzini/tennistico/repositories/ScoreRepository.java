package com.gruzini.tennistico.repositories;

import com.gruzini.tennistico.domain.Score;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ScoreRepository extends JpaRepository<Score, Long> {
}
