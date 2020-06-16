package com.gruzini.tennistico.services;

import com.gruzini.tennistico.domain.Match;
import com.gruzini.tennistico.domain.Player;
import com.gruzini.tennistico.domain.enums.MatchStatus;
import com.gruzini.tennistico.exceptions.WrongMatchStatusException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class JoinMatchService {

    private final MatchService matchService;
    private final PlayerService playerService;

    public JoinMatchService(MatchService matchService, PlayerService playerService) {
        this.matchService = matchService;
        this.playerService = playerService;
    }

    public void joinGuestToMatch(final String guestUsername, final Long matchId) {
        final Match matchToJoin = matchService.getById(matchId);
        validateMatchStatus(matchToJoin);
        final Match match = changeMatchStatus(matchToJoin);
        final Player guest = playerService.getByUsername(guestUsername);
        addMatchToPlayer(guest, match);
    }

    private Match changeMatchStatus(final Match match) {
        match.setMatchStatus(MatchStatus.JOIN_REQUEST);
        matchService.save(match);
        return match;
    }

    private void validateMatchStatus(Match match) {
        final MatchStatus matchStatus = match.getMatchStatus();
        if (!matchStatus.equals(MatchStatus.HOSTED)) {
            throw new WrongMatchStatusException();
        }
    }

    private void addMatchToPlayer(Player guest, Match match) {
        guest.addMatch(match);
        playerService.save(guest);
    }
}
