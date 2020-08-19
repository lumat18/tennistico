package com.gruzini.tennistico.services;

import com.gruzini.tennistico.domain.Match;
import com.gruzini.tennistico.domain.Player;
import com.gruzini.tennistico.domain.enums.MatchStatus;
import com.gruzini.tennistico.events.RejectJoinEvent;
import com.gruzini.tennistico.services.entity_related.MatchService;
import com.gruzini.tennistico.services.entity_related.PlayerService;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Service;

@Service
public class RejectJoinService {

    private final PlayerService playerService;
    private final MatchService matchService;

    public RejectJoinService(PlayerService playerService, MatchService matchService) {
        this.playerService = playerService;
        this.matchService = matchService;
    }

    @EventListener
    public void handleRejectJoinEvent(final RejectJoinEvent event){
        rejectJoin(event.getMatchId(), event.getUsername());
    }

    private void rejectJoin(final Long matchId, final String username) {
        final Player player = playerService.getByUsername(username);
        final Match match = matchService.getById(matchId);
        //validateMatchAndPlayer(match, player);
        matchService.updateMatchStatus(match, MatchStatus.HOSTED);
    }
    //THIS DUPLICATES CODE FROM CONFIRMJOINSERVICE -
    // WE SHOULD EXTRACT VALIDATIONS TO VALIDATOR CLASS -
    // AND EVEN BETTER MAKE JOIN/REJECT GENERIC/DEPENDING ON SOME INFO PASSED IN AS ARGUMENT (EX. STATUS)
}
