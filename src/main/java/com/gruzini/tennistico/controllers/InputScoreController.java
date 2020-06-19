package com.gruzini.tennistico.controllers;

import com.gruzini.tennistico.models.dto.ScoreDTO;
import com.gruzini.tennistico.services.dtos.InputScoreService;
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

    public InputScoreController(final InputScoreService inputScoreService) {
        this.inputScoreService = inputScoreService;
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

        inputScoreService.inputScore(matchId, scoreDTO);
        return "dashboard";
    }
}
