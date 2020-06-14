package com.gruzini.tennistico.services;

import com.gruzini.tennistico.domain.Match;
import com.gruzini.tennistico.domain.Player;
import com.gruzini.tennistico.domain.enums.MatchStatus;
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
        final Match match = changeMatchStatus(matchId);
        final Player guest = playerService.getByUsername(guestUsername);
        addMatchToPlayer(guest, match);
    }

    private Match changeMatchStatus(Long matchId) {
        final Match match = matchService.getById(matchId);
        match.setMatchStatus(MatchStatus.JOIN_REQUEST);
        matchService.save(match);
        return match;
    }

    private void addMatchToPlayer(Player guest, Match match) {
        guest.addMatch(match);
        playerService.save(guest);
    }
}
