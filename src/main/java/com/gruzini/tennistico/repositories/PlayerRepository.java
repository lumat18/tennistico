package com.gruzini.tennistico.repositories;

import com.gruzini.tennistico.domain.Player;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface PlayerRepository extends JpaRepository<Player, Long> {
    @Query("SELECT p FROM players p JOIN users u ON u.player.id=p.id WHERE u.email=:username")
    Optional<Player> findByUsername(final String username);
}
