package com.gruzini.tennistico.mappers;

import com.gruzini.tennistico.domain.Match;
import com.gruzini.tennistico.domain.Player;
import com.gruzini.tennistico.models.dto.matchDto.FutureMatchDto;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class FutureMatchMapper {

    public FutureMatchDto toFutureMatchDto(final Match match) {
        return FutureMatchDto.builder()
                .hostName(match.getHost().getFirstName() + " " + match.getHost().getLastName())
                .guestName(prepareGuestName(match.getGuest()))
                .court(match.getCourt().getName() + ", " + match.getCourt().getCity())
                .start(match.getStartingAt())
                .end(match.getEndingAt())
                .matchStatus(match.getMatchStatus().toString())
                .build();
    }

    private String prepareGuestName(final Optional<Player> guest) {
        return guest.map(player -> player.getFirstName() + " " + player.getLastName()).orElse("");
    }
}
