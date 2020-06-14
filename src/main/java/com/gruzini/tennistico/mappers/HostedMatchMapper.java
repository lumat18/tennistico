package com.gruzini.tennistico.mappers;

import com.gruzini.tennistico.domain.Match;
import com.gruzini.tennistico.models.dto.HostedMatchDto;
import org.springframework.stereotype.Component;

@Component
public class HostedMatchMapper {
    private static final int HOST_INDEX = 0;

    public HostedMatchDto toMatchInfoDto(final Match match) {
        return HostedMatchDto.builder()
                .id(match.getId())
                .hostName(match.getPlayers().get(HOST_INDEX).getFirstName() + " " + match.getPlayers().get(HOST_INDEX).getLastName())
                .hostGender(match.getPlayers().get(HOST_INDEX).getGender().toString())
                .hostAge(match.getPlayers().get(HOST_INDEX).getAge())
                .hostLevel(match.getPlayers().get(HOST_INDEX).getPlayerSkill().name())
                .hostExp(match.getPlayers().get(HOST_INDEX).getYearsOfExperience())
                .address(match.getCourt().getStreet() + ", " + match.getCourt().getCity())
                .start(match.getStartingAt())
                .end(match.getEndingAt())
                .build();
    }
}
