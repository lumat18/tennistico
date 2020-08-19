package com.gruzini.tennistico.mappers;

import com.gruzini.tennistico.domain.Match;
import com.gruzini.tennistico.domain.Player;
import com.gruzini.tennistico.models.dto.matchDto.ArchivedMatchDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class ArchivedMatchMapper {

    private final MatchInfoParser matchInfoParser;

    public ArchivedMatchDto toArchivedMatchDTO(final Match match, final Player player) {
        return ArchivedMatchDto.builder()
                .opponentName(matchInfoParser.getOpponentName(match.getId(), player))
                .opponentId(player.getId())
                .score(matchInfoParser.getScore(match.getId(), player))
                .courtName(match.getCourt().getName() + ", " + match.getCourt().getCity())
                .date(match.getEndingAt().toLocalDate())
                .matchResult(matchInfoParser.getMatchResult(match, player))
                .build();
    }
}

