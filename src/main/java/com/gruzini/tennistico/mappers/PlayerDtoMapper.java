package com.gruzini.tennistico.mappers;

import com.gruzini.tennistico.domain.Player;
import com.gruzini.tennistico.models.dto.PlayerDto;
import com.gruzini.tennistico.repositories.PlayerRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class PlayerDtoMapper {

   private final PlayerRepository playerRepository;

   public PlayerDto toPlayerDto(final Player player){
      return PlayerDto.builder()
              .id(player.getId())
              .firstName(player.getFirstName())
              .lastName(player.getLastName())
              .rankingPoints(player.getRankingPoints())
              .gender(player.getGender().toString())
              .age(player.getAge())
              .photoUrl(player.getPhotoUrl())
              .rankingPosition(getRankingPosition(player.getId()))
              .matchResults(player.getMatchResults())
              .build();
   }

   private String getRankingPosition(final Long id) {
      final Integer playerRankingPosition = playerRepository.getPlayerRankingPosition(id);
      return getOrdinalNumber(playerRankingPosition);
   }

   private String getOrdinalNumber(final Integer number){
      final String[] suffixes = new String[] { "th", "st", "nd", "rd", "th", "th", "th", "th", "th", "th" };
      switch (number % 100) {
         case 11:
         case 12:
         case 13:
            return number + "th";
         default:
            return number + suffixes[number % 10];
      }
   }
}
