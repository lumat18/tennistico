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

   @Scheduled(cron="0 0 * * * *")
   public void clearPendingAndScoreToBeConfirmedGamesOlderThanWeek(){
      System.out.println("Lunched");
      final List<Game> allByGameStatusPendingOrGameStatusConfirm
              = gameRepository.getAllByEndingAtBeforeAndGameStatusIsOrGameStatusIs(LocalDateTime.now().minusHours(168), GameStatus.PENDING, GameStatus.SCORE_TO_BE_CONFIRMED);
      allByGameStatusPendingOrGameStatusConfirm
              .forEach(game -> {
                 System.out.println("Worked");
                 game.setGameStatus(GameStatus.BUSTED);
                 gameRepository.save(game);
              });
   }
}
