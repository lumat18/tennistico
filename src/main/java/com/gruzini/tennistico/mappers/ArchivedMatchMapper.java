package com.gruzini.tennistico.mappers;

import com.gruzini.tennistico.domain.Match;
import com.gruzini.tennistico.domain.Player;
import com.gruzini.tennistico.models.dto.ArchivedMatchDto;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class ArchivedMatchMapper {
   public ArchivedMatchDto toArchivedMatchDTO(final Match match, final Player player) {
      return ArchivedMatchDto.builder()
              .opponentName(getOpponentName(match.getPlayers(), player))
              .score(match.getScore())
              .courtName(match.getCourt().getName() + ", " + match.getCourt().getCity())
              .date(match.getEndingAt().toLocalDate())
              .build();
   }

   private String getOpponentName(final List<Player> players, final Player player) {
      final Player opponent = players.stream().filter(player1 -> !player1.equals(player)).findFirst().orElseThrow();
      return opponent.getFirstName() + " " + opponent.getLastName();
   }
}
