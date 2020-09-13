package com.gruzini.tennistico.mappers;

import com.gruzini.tennistico.domain.Match;
import com.gruzini.tennistico.domain.Player;
import com.gruzini.tennistico.models.dto.matchDto.FutureMatchDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class FutureMatchMapper {
    private final MatchInfoParser matchInfoParser;

    public FutureMatchDto toFutureMatchDto(final Match match, final Player involvedPlayer) {
        return FutureMatchDto.builder()
                .opponentName(matchInfoParser.getOpponentName(match.getId(), involvedPlayer))
                .opponentId(matchInfoParser.getOpponentId(match.getId(), involvedPlayer))
                .court(match.getCourt().getStreet() + ", " + match.getCourt().getCity())
                .start(match.getStartingAt())
                .end(match.getEndingAt())
                .matchStatus(match.getMatchStatus().toString())
                .build();
    }
}
