package com.gruzini.tennistico.services;

import com.gruzini.tennistico.domain.Match;
import com.gruzini.tennistico.domain.Score;
import com.gruzini.tennistico.domain.enums.MatchStatus;
import com.gruzini.tennistico.mappers.ScoreMapper;
import com.gruzini.tennistico.models.dto.ScoreDto;
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

    public void inputScore(final Long matchId, final ScoreDto scoreDto) {
        final Match match = matchService.getById(matchId);
        final Score score = scoreMapper.toScore(scoreDto);
        matchService.updateMatchScore(match, score);
        matchService.updateMatchStatus(match, MatchStatus.SCORE_TO_BE_CONFIRMED);
    }
}
