package com.gruzini.tennistico.repositories;

import com.gruzini.tennistico.domain.Game;
import com.gruzini.tennistico.domain.Player;
import com.gruzini.tennistico.domain.enums.GameStatus;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDateTime;
import java.util.List;

public interface GameRepository extends JpaRepository<Game, Long> {
    List<Game> findAllByGameStatusOrderByStartingAt(GameStatus gameStatus);

    List<Game> getAllByPlayersAndGameStatus(Player player, GameStatus gameStatus);

    List<Game> findByStartingAtBeforeAndGameStatus(LocalDateTime startingDateTime, GameStatus gameStatus);
}
