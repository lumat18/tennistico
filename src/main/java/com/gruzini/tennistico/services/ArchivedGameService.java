package com.gruzini.tennistico.services;

import com.gruzini.tennistico.domain.Game;
import com.gruzini.tennistico.domain.User;
import com.gruzini.tennistico.domain.enums.GameStatus;
import com.gruzini.tennistico.mappers.ArchivedGameMapper;
import com.gruzini.tennistico.models.dto.ArchivedGameDto;
import com.gruzini.tennistico.repositories.GameRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
public class ArchivedGameService {
   private final GameRepository gameRepository;
   private final ArchivedGameMapper mapper;

   public ArchivedGameService(final GameRepository gameRepository, final ArchivedGameMapper mapper) {
      this.gameRepository = gameRepository;
      this.mapper = mapper;
   }

   public List<ArchivedGameDto> getUserGames(final User user){
      final List<Game> allArchivedGamesByPlayer =
              gameRepository.getAllByPlayersAndGameStatus(user.getPlayer(), GameStatus.ARCHIVED);
      return allArchivedGamesByPlayer.stream()
              .map(game -> mapper.toArchivedGameDTO(game, user.getPlayer()))
              .collect(Collectors.toList());
   }
}
