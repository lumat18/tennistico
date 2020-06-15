package com.gruzini.tennistico.controllers;

import com.gruzini.tennistico.exceptions.IllegalActionException;
import com.gruzini.tennistico.models.dto.CreatedMatchDto;
import com.gruzini.tennistico.services.CourtService;
import com.gruzini.tennistico.services.MatchCreationService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.validation.Valid;
import java.security.Principal;

import static java.util.Objects.isNull;

@Controller
@RequestMapping("/dashboard/create")
public class MatchCreationController {

    private final MatchCreationService matchCreationService;
    private final CourtService courtService;

    public MatchCreationController(MatchCreationService matchCreationService, CourtService courtService) {
        this.matchCreationService = matchCreationService;
        this.courtService = courtService;
    }

    @PostMapping("/begin")
    public String beginMatchCreation(@RequestParam(name = "courtId") final Long courtId, final Model model) {
        model.addAttribute("chosenCourt", courtService.getById(courtId));
        model.addAttribute("match", new CreatedMatchDto());
        return "create";
    }

    @PostMapping("/process")
    public String processMatchCreation(@Valid @ModelAttribute("match") final CreatedMatchDto createdMatchDto, final Errors errors, final Model model, final Principal principal) {

        if (errors.hasErrors()) {
            model.addAttribute("chosenCourt", courtService.getById(createdMatchDto.getCourtId()));
            return "create";
        }
        matchCreationService.create(createdMatchDto, principal.getName());
        return "dashboard";
    }
}
