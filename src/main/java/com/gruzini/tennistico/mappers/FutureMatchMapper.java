package com.gruzini.tennistico.mappers;

import com.gruzini.tennistico.domain.Match;
import com.gruzini.tennistico.domain.Player;
import com.gruzini.tennistico.models.dto.matchDto.FutureMatchDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class FutureMatchMapper {

    public FutureMatchDto toFutureMatchDto(final Match match, final Player involvedPlayer) {
        return FutureMatchDto.builder()
                .opponentName(prepareOpponentName(match, involvedPlayer))
                .opponentId(prepareOpponentId(match, involvedPlayer))
                .court(match.getCourt().getName() + ", " + match.getCourt().getCity())
                .start(match.getStartingAt())
                .end(match.getEndingAt())
                .matchStatus(match.getMatchStatus().toString())
                .build();
    }

    private String prepareOpponentName(final Match match, final Player involvedPlayer) {
        if(match.getHost().equals(involvedPlayer)){
            if (match.getGuest().isEmpty()){
                return null;
            }
            return match.getGuest().get().getFullName();
        }
        return match.getHost().getFullName();
    }

    private Long prepareOpponentId(final Match match, final Player involvedPlayer) {
        if(match.getHost().equals(involvedPlayer)){
            if(match.getGuest().isEmpty()){
                return null;
            }
            return match.getGuest().get().getId();
        }
        return match.getHost().getId();
    }
}
