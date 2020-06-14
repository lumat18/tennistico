package com.gruzini.tennistico.controllers;

import com.gruzini.tennistico.domain.Match;
import com.gruzini.tennistico.domain.Player;
import com.gruzini.tennistico.models.score.Score;
import com.gruzini.tennistico.models.score.ScoreMapper;
import com.gruzini.tennistico.models.score.WinValidator;
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
    private final WinValidator winValidator;

    public InputScoreController(MatchService matchService, ScoreMapper scoreMapper, WinValidator winValidator) {
        this.matchService = matchService;
        this.scoreMapper = scoreMapper;
        this.winValidator = winValidator;
    }

    @PostMapping
    public String inputScore(final Long matchId,
                             @Valid @ModelAttribute(name = "score") final Score score,
                             final Errors errors,
                             final Model model) {
        if (errors.hasErrors()) {
            model.addAttribute("message", "Invalid score");
            return "dashboard";
        }

        //TODO wyciągnąć grę po id
        final Match match = matchService.getById(matchId);
        //TODO przeliczyć score na stringa, który pójdzie do encji
        match.setScore(scoreMapper.mapScoreToString(score));
        //TODO sprawdzić kto wygrał
        final Player winner = winValidator.validateWinner(match);
        //TODO zrobić update WIN/LOSS playerów


        return "dashboard";
    }
}
