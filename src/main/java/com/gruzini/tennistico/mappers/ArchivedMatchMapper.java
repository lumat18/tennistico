package com.gruzini.tennistico.mappers;

import com.gruzini.tennistico.domain.Match;
import com.gruzini.tennistico.domain.Player;
import com.gruzini.tennistico.models.dto.PlayerDto;
import com.gruzini.tennistico.models.dto.matchDto.ArchivedMatchDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class ArchivedMatchMapper {

    private final MatchInfoParser matchInfoParser;
    private final PlayerDtoMapper playerDtoMapper;

    public ArchivedMatchDto toArchivedMatchDTO(final Match match, final Player player) {
        return ArchivedMatchDto.builder()
                .opponent(getOpponentDto(match, player))
                .score(matchInfoParser.getScore(match.getId(), player))
                .courtName(match.getCourt().getName() + ", " + match.getCourt().getCity())
                .date(match.getEndingAt().toLocalDate())
                .build();
    }

    private PlayerDto getOpponentDto(Match match, Player player) {
        return playerDtoMapper.toPlayerDto(matchInfoParser.getOpponent(match, player));
    }


}

