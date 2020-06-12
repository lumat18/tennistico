package com.gruzini.tennistico.repositories;

import com.gruzini.tennistico.domain.Game;
import com.gruzini.tennistico.domain.Player;
import com.gruzini.tennistico.domain.enums.GameStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDateTime;
import java.util.List;

public interface GameRepository extends JpaRepository<Game, Long> {

    List<Game> findAllByGameStatusIsAndPlayersNotContainsAndStartingAtIsAfterOrderByStartingAt(GameStatus status, Player player, LocalDateTime now);
    List<Game> getAllByPlayersAndGameStatus(Player player, GameStatus gameStatus);
}
