package com.gruzini.tennistico.controllers;

import com.gruzini.tennistico.domain.Match;
import com.gruzini.tennistico.domain.enums.NotificationType;
import com.gruzini.tennistico.models.dto.matchDto.CreatedMatchDto;
import com.gruzini.tennistico.services.MatchCreationService;
import com.gruzini.tennistico.services.NotificationSenderService;
import com.gruzini.tennistico.services.dtos.CourtDtoService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.validation.Valid;
import java.security.Principal;

@Controller
@RequestMapping("/dashboard/create")
public class MatchCreationController {

    private final MatchCreationService matchCreationService;
    private final CourtDtoService courtDtoService;
    private final NotificationSenderService notificationSender;

    public MatchCreationController(final MatchCreationService matchCreationService,
                                   final CourtDtoService courtDtoService,
                                   final NotificationSenderService notificationSender) {
        this.matchCreationService = matchCreationService;
        this.courtDtoService = courtDtoService;
        this.notificationSender = notificationSender;
    }

    @PostMapping("/begin")
    public String beginMatchCreation(@RequestParam(name = "courtId") final Long courtId, final Model model) {
        model.addAttribute("chosenCourt", courtDtoService.getCourtInfoById(courtId));
        model.addAttribute("match", new CreatedMatchDto());
        return "create";
    }

    @PostMapping("/process")
    public String processMatchCreation(@Valid @ModelAttribute("match") final CreatedMatchDto createdMatchDto, final Errors errors, final Model model, final Principal principal) {

        if (errors.hasErrors()) {
            model.addAttribute("chosenCourt", courtDtoService.getCourtInfoById(createdMatchDto.getCourtId()));
            return "create";
        }
        final Match match = matchCreationService.create(createdMatchDto, principal.getName());
        notificationSender.sendToHost(match.getId(), NotificationType.MATCH_CREATED);
        return "dashboard";
    }
}
