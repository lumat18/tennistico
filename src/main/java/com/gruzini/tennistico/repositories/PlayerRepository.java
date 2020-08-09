package com.gruzini.tennistico.repositories;

import com.gruzini.tennistico.domain.Player;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface PlayerRepository extends JpaRepository<Player, Long> {
    @Query("SELECT p FROM players p JOIN users u ON u.player.id=p.id WHERE u.email=:username")
    Optional<Player> findByUsername(final String username);

    @Query(value = "SELECT num FROM (SELECT p.id AS pid, RANK() OVER (ORDER BY p.ranking_points DESC) AS num FROM players p) WHERE pid = ?1", nativeQuery = true)
    Integer getPlayerRankingPosition(final Long id);

    Page<Player> findAllByOrderByRankingPointsDesc(Pageable pageable);
}
