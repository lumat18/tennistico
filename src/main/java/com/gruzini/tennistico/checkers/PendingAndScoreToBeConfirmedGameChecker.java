package com.gruzini.tennistico.checkers;

import com.gruzini.tennistico.domain.Game;
import com.gruzini.tennistico.domain.enums.GameStatus;
import com.gruzini.tennistico.repositories.GameRepository;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class PendingAndScoreToBeConfirmedGameChecker {
   private final GameRepository gameRepository;

   public PendingAndScoreToBeConfirmedGameChecker(final GameRepository gameRepository) {
      this.gameRepository = gameRepository;
   }

   @Scheduled(cron="0 0/5 * * * *")
   public void clearPendingAndScoreToBeConfirmedGamesOlderThanWeek(){
      final List<Game> allByGameStatusPendingOrGameStatusConfirm
              = gameRepository.getAllByGameStatusIsOrGameStatusIs(GameStatus.PENDING, GameStatus.SCORE_TO_BE_CONFIRMED);
      allByGameStatusPendingOrGameStatusConfirm.stream()
              .filter(game -> game.getEndingAt().plusWeeks(1).isBefore(LocalDateTime.now()))
              .collect(Collectors.toList())
              .forEach(game -> {
                 game.setGameStatus(GameStatus.BUSTED);
                 gameRepository.save(game);
              });
   }
}
