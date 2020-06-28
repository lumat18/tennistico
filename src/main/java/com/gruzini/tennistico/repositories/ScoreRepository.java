package com.gruzini.tennistico.repositories;

import com.gruzini.tennistico.domain.Score;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import javax.security.sasl.SaslException;
import java.util.Optional;

public interface ScoreRepository extends JpaRepository<Score, Long> {
    @Query("SELECT s FROM scores s LEFT JOIN FETCH s.sets WHERE s.id = :id")
    Optional<Score> getById(@Param("id") Long id);
}
