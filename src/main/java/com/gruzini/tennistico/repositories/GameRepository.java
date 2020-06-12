package com.gruzini.tennistico.repositories;

import com.gruzini.tennistico.domain.Game;
import com.gruzini.tennistico.domain.Player;
import com.gruzini.tennistico.domain.enums.GameStatus;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

public interface GameRepository extends JpaRepository<Game, Long> {

    List<Game> findAllByGameStatusAndPlayersNotContainsAndStartingAtIsAfterOrderByStartingAt(GameStatus status, Player player, LocalDateTime now);
    List<Game> getAllByPlayersAndGameStatus(Player player, GameStatus gameStatus);
    Optional<Game> findById(Long id);
    List<Game> getAllByEndingAtBeforeAndGameStatusIsOrGameStatusIs(LocalDateTime time, GameStatus pending, GameStatus confirm);
}
