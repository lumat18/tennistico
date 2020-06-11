package com.gruzini.tennistico.services;

import com.gruzini.tennistico.domain.Game;
import com.gruzini.tennistico.domain.Player;
import com.gruzini.tennistico.domain.enums.GameStatus;
import com.gruzini.tennistico.repositories.GameRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class GameService {
   private final GameRepository gameRepository;

   public GameService(final GameRepository gameRepository) {
      this.gameRepository = gameRepository;
   }

   public List<Game> findAllArchivedGamesByPlayer(final Player player, final GameStatus gameStatus){
      return gameRepository.getAllByPlayersAndGameStatus(player, gameStatus);
   }
}
