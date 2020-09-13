package com.gruzini.tennistico.controllers;

import com.gruzini.tennistico.events.CreateMatchEvent;
import com.gruzini.tennistico.models.dto.matchDto.CreatedMatchDto;
import com.gruzini.tennistico.models.forms.CourtForm;
import com.gruzini.tennistico.services.dto_related.CourtDtoService;
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
@RequestMapping("/create")
public class MatchCreationController {

    private final ApplicationEventPublisher applicationEventPublisher;
    private final CourtDtoService courtDtoService;

    public MatchCreationController(ApplicationEventPublisher applicationEventPublisher, CourtDtoService courtDtoService) {
        this.applicationEventPublisher = applicationEventPublisher;
        this.courtDtoService = courtDtoService;
    }

    @PostMapping("/begin")
    public String beginMatchCreation(@ModelAttribute("courtForm") final CourtForm courtForm,
                                     final Model model) {
        model.addAttribute("chosenCourt", courtDtoService.getCourtInfoForMatchCreation(courtForm));
        model.addAttribute("match", new CreatedMatchDto());
        return "create";
    }

    @PostMapping("/process")
    public String processMatchCreation(@Valid @ModelAttribute("match") final CreatedMatchDto createdMatchDto,
                                       final Errors errors, final Model model, final Principal principal) {
        if (errors.hasErrors()) {
            model.addAttribute("chosenCourt", courtDtoService.getCourtInfoById(createdMatchDto.getCourtId()));
            return "create";
        }
        applicationEventPublisher.publishEvent(new CreateMatchEvent(this, createdMatchDto, principal.getName()));
        return "redirect:/dashboard";
    }
}
