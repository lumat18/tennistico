package com.gruzini.tennistico.mappers;

import com.gruzini.tennistico.domain.Game;
import com.gruzini.tennistico.domain.Player;
import com.gruzini.tennistico.models.dto.ArchivedGameDto;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class ArchivedGameMapper {
   public ArchivedGameDto toArchivedGameDTO(final Game game, final Player player){
      return ArchivedGameDto.builder()
              .opponentName(getOpponentName(game.getPlayers(), player))
              .score(game.getScore())
              .courtName(game.getCourt().getName() + ", " + game.getCourt().getCity())
              .date(game.getEndingAt().toLocalDate())
              .build();
   }

   private String getOpponentName(final List<Player> players, final Player player){
      final Player opponent = players.stream().filter(player1 -> !player1.equals(player)).findFirst().orElseThrow();
      return opponent.getFirstName() + " " + opponent.getLastName();
   }
}
