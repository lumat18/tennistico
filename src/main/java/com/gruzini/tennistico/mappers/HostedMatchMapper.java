package com.gruzini.tennistico.mappers;

import com.gruzini.tennistico.domain.Match;
import com.gruzini.tennistico.models.dto.matchDto.HostedMatchDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class HostedMatchMapper {

    private final PlayerDtoMapper playerDtoMapper;

    public HostedMatchDto toMatchInfoDto(final Match match) {
        return HostedMatchDto.builder()
                .id(match.getId())
                .host(playerDtoMapper.toPlayerDto(match.getHost()))
                .address(match.getCourt().getStreet() + ", " + match.getCourt().getCity())
                .start(match.getStartingAt())
                .end(match.getEndingAt())
                .build();
    }
}
