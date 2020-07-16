package com.gruzini.tennistico.mappers;

import com.gruzini.tennistico.domain.Player;
import com.gruzini.tennistico.models.dto.PlayerDto;
import org.springframework.stereotype.Component;

@Component
public class PlayerDtoMapper {
   public PlayerDto toPlayerDto(final Player player){
      return PlayerDto.builder()
              .firstName(player.getFirstName())
              .lastName(player.getLastName())
              .rankingPoints(player.getRankingPoints())
              .gender(player.getGender().toString())
              .age(player.getAge())
              .build();
   }
}
