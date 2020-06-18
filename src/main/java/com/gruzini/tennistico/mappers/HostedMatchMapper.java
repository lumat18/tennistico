package com.gruzini.tennistico.mappers;

import com.gruzini.tennistico.domain.Match;
import com.gruzini.tennistico.models.dto.matchDto.HostedMatchDto;
import org.springframework.stereotype.Component;

@Component
public class HostedMatchMapper {

    public HostedMatchDto toMatchInfoDto(final Match match) {
        return HostedMatchDto.builder()
                .id(match.getId())
                .hostName(match.getHost().getFirstName() + " " + match.getHost().getLastName())
                .hostGender(match.getHost().getGender().toString())
                .hostAge(match.getHost().getAge())
                .hostLevel(match.getHost().getPlayerSkill().name())
                .hostExp(match.getHost().getYearsOfExperience())
                .address(match.getCourt().getStreet() + ", " + match.getCourt().getCity())
                .start(match.getStartingAt())
                .end(match.getEndingAt())
                .build();
    }
}
