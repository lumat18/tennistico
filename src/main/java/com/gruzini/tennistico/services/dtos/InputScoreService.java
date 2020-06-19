package com.gruzini.tennistico.services.dtos;

import com.gruzini.tennistico.domain.Match;
import com.gruzini.tennistico.domain.enums.MatchStatus;
import com.gruzini.tennistico.mappers.ScoreMapper;
import com.gruzini.tennistico.models.dto.ScoreDTO;
import com.gruzini.tennistico.services.RankingService;
import com.gruzini.tennistico.services.entities.MatchService;
import com.gruzini.tennistico.services.score.WinDecider;
import org.springframework.stereotype.Service;

@Service
public class InputScoreService {

    private final MatchService matchService;
    private final RankingService rankingService;
    private final WinDecider winDecider;
    private final ScoreMapper scoreMapper;

    public InputScoreService(final MatchService matchService,
                             final RankingService rankingService,
                             final WinDecider winDecider,
                             final ScoreMapper scoreMapper) {
        this.matchService = matchService;
        this.rankingService = rankingService;
        this.winDecider = winDecider;
        this.scoreMapper = scoreMapper;
    }

    public void inputScore(final Long matchId, final ScoreDTO scoreDTO) {
        final Match match = matchService.getById(matchId);
        final String score = scoreMapper.mapScoreToString(scoreDTO);
        updateMatchAndRanking(match, score);
    }

    private void updateMatchAndRanking(final Match match, final String score){
        matchService.updateMatchScore(match, score);

        rankingService.updateWinner(winDecider.decideWinner(match));
        rankingService.updateLoser(winDecider.decideLoser(match));

        matchService.updateMatchStatus(match, MatchStatus.SCORE_TO_BE_CONFIRMED);
    }
}
