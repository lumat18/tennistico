package com.gruzini.tennistico.controllers;

import com.gruzini.tennistico.events.InputScoreEvent;
import com.gruzini.tennistico.models.dto.ScoreDto;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.validation.Valid;
import java.security.Principal;

@Controller
@RequestMapping("/input-score")
public class InputScoreController {

    private final ApplicationEventPublisher applicationEventPublisher;

    public InputScoreController(ApplicationEventPublisher applicationEventPublisher) {
        this.applicationEventPublisher = applicationEventPublisher;
    }

    @PostMapping
    public String inputScore(final Long matchId,
                             @Valid @ModelAttribute(name = "score") final ScoreDto scoreDto,
                             final Errors errors,
                             final Model model,
                             final Principal principal) {
        if (errors.hasErrors()) {
            model.addAttribute("message", "Invalid score");
            return "dashboard";
        }
        applicationEventPublisher.publishEvent(new InputScoreEvent(this, matchId, principal.getName(), scoreDto));
        return "dashboard";
    }
}
