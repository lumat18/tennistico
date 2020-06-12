package com.gruzini.tennistico.checkers;

import com.gruzini.tennistico.domain.enums.GameStatus;
import com.gruzini.tennistico.repositories.GameRepository;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class PendingAndScoreToBeConfirmedGameChecker {
   private final GameRepository gameRepository;

   public PendingAndScoreToBeConfirmedGameChecker(final GameRepository gameRepository) {
      this.gameRepository = gameRepository;
   }

   @Scheduled(cron="*/10 * * * * *")
   public void clearPendingAndScoreToBeConfirmedGamesOlderThanWeek(){
      
   }
}
