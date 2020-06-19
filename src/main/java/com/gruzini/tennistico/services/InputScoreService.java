package com.gruzini.tennistico.services;

import com.gruzini.tennistico.domain.Match;
import com.gruzini.tennistico.domain.Score;
import com.gruzini.tennistico.domain.enums.MatchStatus;
import com.gruzini.tennistico.events.InputScoreEvent;
import com.gruzini.tennistico.mappers.ScoreMapper;
import org.springframework.context.event.EventListener;
import com.gruzini.tennistico.services.entities.MatchService;
import org.springframework.stereotype.Service;

@Service
public class InputScoreService {

    private final MatchService matchService;
    private final ScoreMapper scoreMapper;

    public InputScoreService(final MatchService matchService,
                             final ScoreMapper scoreMapper) {
        this.matchService = matchService;
        this.scoreMapper = scoreMapper;
    }

    @EventListener
    public void handleInputScoreEvent(final InputScoreEvent event) {
        inputScore(event.getMatchId(), score);
    }

    public void inputScore(final Long matchId, final ScoreDto scoreDto) {
        final Match match = matchService.getById(matchId);
        final Score score = scoreMapper.toScore(scoreDto);
        matchService.updateMatchScore(match, score);
        matchService.updateMatchStatus(match, MatchStatus.SCORE_TO_BE_CONFIRMED);
    }
}
