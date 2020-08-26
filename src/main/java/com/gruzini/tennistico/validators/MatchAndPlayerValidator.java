package com.gruzini.tennistico.validators;

import com.gruzini.tennistico.domain.Match;
import com.gruzini.tennistico.domain.Player;
import com.gruzini.tennistico.domain.enums.MatchStatus;
import com.gruzini.tennistico.exceptions.*;
import org.springframework.stereotype.Component;

import static java.util.Objects.isNull;

@Component
public class MatchAndPlayerValidator {

    public void bothPlayersExist(final Match match) {
        if (isNull(match.getHost()) || isNull(match.getGuest())) {
            throw new MatchPlayersException();
        }
    }

    public void isMatchStatusCorrect(final MatchStatus status, final MatchStatus correctStatus) {
        if (!status.equals(correctStatus)) {
            throw new WrongMatchStatusException();
        }
    }

    public void isPlayerHost(final Match match, final Player player) {
        if (!match.getHost().equals(player)) {
            throw new PlayerIsNotAMatchHostException();
        }
    }

    public void isPlayerGuest(final Match match, final Player player) {
        final Player guest = match.getGuest().orElseThrow(NoGuestInMatchException::new);
        if (!guest.equals(player)) {
            throw new PlayerIsNotMatchGuestException();
        }
    }
}
