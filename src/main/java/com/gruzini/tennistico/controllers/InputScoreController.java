package com.gruzini.tennistico.controllers;

import com.gruzini.tennistico.domain.Match;
import com.gruzini.tennistico.domain.enums.MatchStatus;
import com.gruzini.tennistico.models.score.ScoreDTO;
import com.gruzini.tennistico.models.score.ScoreMapper;
import com.gruzini.tennistico.services.MatchService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.validation.Valid;

@Controller
@RequestMapping("/input-score")
public class InputScoreController {
    private final MatchService matchService;
    private final ScoreMapper scoreMapper;


    public InputScoreController(MatchService matchService, ScoreMapper scoreMapper) {
        this.matchService = matchService;
        this.scoreMapper = scoreMapper;
    }

    @PostMapping
    public String inputScore(final Long matchId,
                             @Valid @ModelAttribute(name = "score") final ScoreDTO scoreDTO,
                             final Errors errors,
                             final Model model) {
        if (errors.hasErrors()) {
            model.addAttribute("message", "Invalid score");
            return "dashboard";
        }

        //TODO wyciągnąć grę po id
        final Match match = matchService.getById(matchId);
        //TODO przeliczyć score na stringa, który pójdzie do encji
        final String score = scoreMapper.mapScoreToString(scoreDTO);
        match.setScore(score);
        //TODO sprawdzić kto wygrał

        //TODO zrobić update WIN/LOSS playerów

        //TODO zmienić status gry z PENDING na SCORE_TO_BE_CONFIRMED
        matchService.updateMatchStatus(match, MatchStatus.SCORE_TO_BE_CONFIRMED);

        return "dashboard";
    }
}
