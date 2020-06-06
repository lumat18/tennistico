package com.gruzini.tennistico.mappers;

import com.gruzini.tennistico.domain.Player;
import com.gruzini.tennistico.models.PlayerDTO;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.time.Period;

import static java.util.Objects.nonNull;

@Component
public class PlayerMapper {
    public Player mapPlayerDTOtoPlayer(final PlayerDTO playerDTO) {
        return Player.builder()
                .firstName(playerDTO.getFirstName())
                .lastName(playerDTO.getLastName())
                .gender(playerDTO.getGender())
                .birthday(playerDTO.getBirthday())
                .description(playerDTO.getDescription())
                .playerSkill(playerDTO.getPlayerSkill())
                .build();
    }

    public PlayerDTO mapPlayerToPlayerDTO(final Player player){
        return PlayerDTO.builder()
                .firstName(player.getFirstName())
                .lastName(player.getLastName())
                .birthday(player.getBirthday())
                .age(calculateAge(player.getBirthday()))
                .gender(player.getGender())
                .description(player.getDescription())
                .playerSkill(player.getPlayerSkill())
                .build();
    }

    private Integer calculateAge(LocalDate birthday) {
        if (nonNull(birthday)) {
            return Period.between(birthday, LocalDate.now()).getYears();
        }
        return 0;
    }
}
