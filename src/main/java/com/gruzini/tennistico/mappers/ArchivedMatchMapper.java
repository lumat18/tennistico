package com.gruzini.tennistico.mappers;

import com.gruzini.tennistico.domain.Match;
import com.gruzini.tennistico.domain.Player;
import com.gruzini.tennistico.domain.Set;
import com.gruzini.tennistico.exceptions.PlayerNotFoundException;
import com.gruzini.tennistico.models.dto.PlayerDto;
import com.gruzini.tennistico.models.dto.matchDto.ArchivedMatchDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class ArchivedMatchMapper {

    private final PlayerDtoMapper playerDtoMapper;

    public ArchivedMatchDto toArchivedMatchDTO(final Match match, final Player player) {
        return ArchivedMatchDto.builder()
                .opponent(getOpponent(match, player))
                .score(getScoreToString(match, player))
                .courtName(match.getCourt().getName() + ", " + match.getCourt().getCity())
                .date(match.getEndingAt().toLocalDate())
                .matchResult(getMatchResult(match, player))
                .build();
    }

    private PlayerDto getOpponent(final Match match, final Player player){
        Player opponent;
        if (match.getHost().equals(player)){
            opponent = match.getGuest().orElseThrow(PlayerNotFoundException::new);
        } else {
            opponent = match.getHost();
        }
        return playerDtoMapper.toPlayerDto(opponent);
    }

    private String getScoreToString(final Match match, final Player player) {
        int firstDigit = 0;
        int secondDigit = 0;
        for (Set set : match.getScore().getSets()) {
            if(match.getHost().equals(player)){
                if(set.getHostScore() > set.getGuestScore()){
                    firstDigit++;
                } else {
                    secondDigit++;
                }
            } else {
                if(set.getGuestScore() > set.getHostScore()){
                    firstDigit++;
                } else {
                    secondDigit++;
                }
            }
        }
        return firstDigit + " - " + secondDigit;
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
