package com.gruzini.tennistico.repositories;

import com.gruzini.tennistico.domain.Player;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PlayerRepository extends JpaRepository<Player, Long> {
}
