package com.gruzini.tennistico.mappers;

import com.gruzini.tennistico.domain.Match;
import com.gruzini.tennistico.domain.Player;
import com.gruzini.tennistico.models.dto.PlayerDto;
import com.gruzini.tennistico.models.dto.matchDto.FutureMatchDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class FutureMatchMapper {

    private final PlayerDtoMapper playerDtoMapper;

    public FutureMatchDto toFutureMatchDto(final Match match, final Player involved) {
        return FutureMatchDto.builder()
                .host(playerDtoMapper.toPlayerDto(match.getHost()))
                .guest(prepareGuest(match))
                .court(match.getCourt().getName() + ", " + match.getCourt().getCity())
                .start(match.getStartingAt())
                .end(match.getEndingAt())
                .matchStatus(match.getMatchStatus().toString())
                .isInvolvedPlayerHost(match.getHost().equals(involved))
                .build();
    }

    private PlayerDto prepareGuest(final Match match) {
        if (match.getGuest().isEmpty()){
            return null;
        }
        return playerDtoMapper.toPlayerDto(match.getGuest().get());
    }
}
