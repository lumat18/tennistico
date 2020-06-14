package com.gruzini.tennistico.controllers;

import com.gruzini.tennistico.domain.Match;
import com.gruzini.tennistico.models.score.Score;
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

    public InputScoreController(MatchService matchService) {
        this.matchService = matchService;
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
        //TODO sprawdzić kto wygrał
        //TODO zrobić update WIN/LOSS playerów


        return "dashboard";
    }
}
