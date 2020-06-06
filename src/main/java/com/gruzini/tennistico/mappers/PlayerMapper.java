package com.gruzini.tennistico.mappers;

import com.gruzini.tennistico.domain.Player;
import com.gruzini.tennistico.models.PlayerDTO;
import org.springframework.stereotype.Component;

@Component
public class PlayerMapper {
    public Player mapPlayerDTOtoPlayer(final PlayerDTO playerDTO){
        return Player.builder()
                .firstName(playerDTO.getFirstName())
                .lastName(playerDTO.getLastName())
                .gender(playerDTO.getGender())
                .birthday(playerDTO.getBirthday())
                .description(playerDTO.getDescription())
                .playerSkill(playerDTO.getPlayerSkill())
                .build();
    }
}
