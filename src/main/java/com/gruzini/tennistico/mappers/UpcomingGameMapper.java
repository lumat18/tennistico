package com.gruzini.tennistico.mappers;

import com.gruzini.tennistico.domain.Game;
import com.gruzini.tennistico.models.dto.UpcomingGameDto;
import org.springframework.stereotype.Component;

@Component
public class UpcomingGameMapper {
    private static final int HOST_INDEX = 0;

    public UpcomingGameDto toGameInfoDto(final Game game) {
        return UpcomingGameDto.builder()
                .hostName(game.getPlayers().get(HOST_INDEX).getFirstName() + " " + game.getPlayers().get(HOST_INDEX).getLastName())
                .hostGender(game.getPlayers().get(HOST_INDEX).getGender().toString())
                .hostAge(game.getPlayers().get(HOST_INDEX).getAge())
                .hostLevel(game.getPlayers().get(HOST_INDEX).getPlayerSkill().name())
                .hostExp(game.getPlayers().get(HOST_INDEX).getYearsOfExperience())
                .address(game.getCourt().getStreet() + ", " + game.getCourt().getCity())
                .start(game.getStartingAt())
                .end(game.getEndingAt())
                .build();
    }
}
