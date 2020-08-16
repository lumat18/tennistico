package com.gruzini.tennistico.mappers;

import com.gruzini.tennistico.domain.Match;
import com.gruzini.tennistico.models.dto.matchDto.HostedMatchDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class HostedMatchMapper {

    public HostedMatchDto toMatchInfoDto(final Match match) {
        return HostedMatchDto.builder()
                .id(match.getId())
                .hostName(match.getHost().getFullName())
                .hostId(match.getHost().getId())
                .address(match.getCourt().getStreet() + ", " + match.getCourt().getCity())
                .start(match.getStartingAt())
                .end(match.getEndingAt())
                .build();
    }
}
