package com.gruzini.tennistico.mappers;

import com.gruzini.tennistico.domain.Match;
import com.gruzini.tennistico.domain.Player;
import com.gruzini.tennistico.domain.Score;
import com.gruzini.tennistico.exceptions.PlayerNotFoundException;
import com.gruzini.tennistico.models.dto.PlayerDto;
import com.gruzini.tennistico.models.dto.matchDto.ArchivedMatchDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
public class ArchivedMatchMapper {

    private final MatchInfoParser matchInfoParser;
    public ArchivedMatchDto toArchivedMatchDTO(final Match match, final Player player) {
        return ArchivedMatchDto.builder()
                .opponentName(getOpponentFullName(match, player))
                .opponentId(player.getId())
                .score(matchInfoParser.getScore(match.getId(), player))
                .courtName(match.getCourt().getName() + ", " + match.getCourt().getCity())
                .date(match.getEndingAt().toLocalDate())
                .matchResult(getMatchResult(match, player))
                .build();
    }

    private String getOpponentFullName(final Match match, final Player player){
        Player opponent;
        if (match.getHost().equals(player)){
            opponent = match.getGuest().orElseThrow(PlayerNotFoundException::new);
        } else {
            opponent = match.getHost();
        }
        return opponent.getFullName();
    }

    private String getMatchResult(final Match match, final Player player) {
        if(match.getScore().isDraw()){
            return "DRAW";
        } else {
            if(match.getScore().getWinner().equals(player)){
                return "WIN";
            } else {
                return "LOSS";
            }
        }
    }
}

