package com.gruzini.tennistico.controllers;

import com.gruzini.tennistico.domain.Score;
import com.gruzini.tennistico.mappers.ScoreMapper;
import com.gruzini.tennistico.models.dto.ScoreDto;
import com.gruzini.tennistico.services.InputScoreService;
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

    private final InputScoreService inputScoreService;
    private final ScoreMapper scoreMapper;

    public InputScoreController(InputScoreService inputScoreService, ScoreMapper scoreMapper) {
        this.inputScoreService = inputScoreService;

        this.scoreMapper = scoreMapper;
    }

    @PostMapping
    public String inputScore(final Long matchId,
                             @Valid @ModelAttribute(name = "score") final ScoreDto scoreDTO,
                             final Errors errors,
                             final Model model) {
        if (errors.hasErrors()) {
            model.addAttribute("message", "Invalid score");
            return "dashboard";
        }

        final Score score = scoreMapper.toScore(scoreDTO);
        inputScoreService.inputScore(matchId, score);

        return "dashboard";
    }
}
