package com.gruzini.tennistico.repositories;

import com.gruzini.tennistico.domain.Game;
import org.springframework.data.jpa.repository.JpaRepository;

public interface GameRepository extends JpaRepository<Game, Long> {
}
