package com.gruzini.tennistico.mappers;

import com.gruzini.tennistico.domain.Game;
import com.gruzini.tennistico.models.dto.GameInfoDto;
import org.springframework.stereotype.Component;

@Component
public class GameInfoMapper {

    public GameInfoDto toGameInfoDto(final Game game){
        return GameInfoDto.builder()
                .hostName(game.getPlayers().get(0).getFirstName()+" "+game.getPlayers().get(0).getLastName())
                .hostGender(game.getPlayers().get(0).getGender().toString())
                .hostAge(game.getPlayers().get(0).getAge())
                .hostLevel(game.getPlayers().get(0).getPlayerSkill().name())
                .hostExp(game.getPlayers().get(0).getYearsOfExperience())
                .address(game.getCourt().getStreet()+", "+game.getCourt().getCity())
                .start(game.getStartingAt())
                .end(game.getEndingAt())
                .build();
    }
}
