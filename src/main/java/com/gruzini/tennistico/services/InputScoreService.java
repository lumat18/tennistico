package com.gruzini.tennistico.services;

import com.gruzini.tennistico.domain.Match;
import com.gruzini.tennistico.domain.Score;
import com.gruzini.tennistico.domain.enums.MatchStatus;
import com.gruzini.tennistico.events.InputScoreEvent;
import com.gruzini.tennistico.mappers.ScoreMapper;
import com.gruzini.tennistico.models.dto.ScoreDto;
import com.gruzini.tennistico.services.entity_related.MatchService;
import com.gruzini.tennistico.services.entity_related.ScoreService;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Service;

@Service
public class InputScoreService {

    private final MatchService matchService;
    private final ScoreMapper scoreMapper;
    private final ScoreService scoreService;

    public InputScoreService(final MatchService matchService,
                             final ScoreMapper scoreMapper,
                             final ScoreService scoreService) {
        this.matchService = matchService;
        this.scoreMapper = scoreMapper;
        this.scoreService = scoreService;
    }

    @EventListener
    public void handleInputScoreEvent(final InputScoreEvent event) {
        inputScore(event.getMatchId(), event.getScoreDto());
    }

    public void inputScore(final Long matchId, final ScoreDto scoreDto) {
        final Match match = matchService.getById(matchId);
        final Score score = scoreService.save(scoreMapper.toScore(scoreDto));
        updateMatchScore(match, score);
        matchService.updateMatchStatus(match, MatchStatus.SCORE_TO_BE_CONFIRMED);
    }

    private void updateMatchScore(final Match match, final Score score){
        if (match.getMatchStatus() == MatchStatus.PENDING){
            match.setScore(score);
            matchService.save(match);
        }
    }
}
