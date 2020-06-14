package com.gruzini.tennistico.controllers;

import com.gruzini.tennistico.exceptions.IllegalActionException;
import com.gruzini.tennistico.models.dto.CreatedMatchDto;
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

    public MatchCreationController(MatchCreationService matchCreationService) {
        this.matchCreationService = matchCreationService;
    }

    @PostMapping("/begin")
    public String beginMatchCreation(@RequestParam(name = "courtId") final Long courtId, final Model model) {
        if (isNull(courtId)) throw new IllegalActionException();
        model.addAttribute("chosenCourtId", courtId);
        model.addAttribute("match", CreatedMatchDto.builder().build());
        return "create";
    }

    @PostMapping("/process")
    public String processMatchCreation(@Valid @ModelAttribute("match") final CreatedMatchDto createdMatchDto, final Errors errors, final Model model, final Principal principal) {

        if (errors.hasErrors()) {
            model.addAttribute("chosenCourtId", createdMatchDto.getCourtId());
            return "create";
        }
        matchCreationService.create(createdMatchDto, principal.getName());
        return "dashboard";
    }
}
