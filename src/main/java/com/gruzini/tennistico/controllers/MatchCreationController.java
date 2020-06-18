package com.gruzini.tennistico.controllers;

import com.gruzini.tennistico.events.CreateMatchEvent;
import com.gruzini.tennistico.models.dto.CreatedMatchDto;
import com.gruzini.tennistico.services.CourtService;
import com.gruzini.tennistico.services.MatchCreationService;
import com.gruzini.tennistico.services.NotificationSenderService;
import org.springframework.context.ApplicationEventPublisher;
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
    private final CourtService courtService;
    private final NotificationSenderService notificationSender;

    private final ApplicationEventPublisher applicationEventPublisher;

    public MatchCreationController(MatchCreationService matchCreationService, CourtService courtService, NotificationSenderService notificationSender, ApplicationEventPublisher applicationEventPublisher) {
        this.matchCreationService = matchCreationService;
        this.courtService = courtService;
        this.notificationSender = notificationSender;
        this.applicationEventPublisher = applicationEventPublisher;
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

        applicationEventPublisher.publishEvent(new CreateMatchEvent(this, createdMatchDto, principal.getName()));

//        final Match match = matchCreationService.create(createdMatchDto, principal.getName());
//        notificationSender.sendToHost(match.getId(), NotificationType.MATCH_CREATED);
        return "dashboard";
    }
}
