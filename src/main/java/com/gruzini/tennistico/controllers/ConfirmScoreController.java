package com.gruzini.tennistico.controllers;

import com.gruzini.tennistico.domain.enums.NotificationType;
import com.gruzini.tennistico.services.ConfirmScoreService;
import com.gruzini.tennistico.services.NotificationSenderService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.security.Principal;

@Controller
@RequestMapping("/dashboard/confirm-score")
public class ConfirmScoreController {

    private final ConfirmScoreService confirmScoreService;
    private final NotificationSenderService notificationSender;

    public ConfirmScoreController(final ConfirmScoreService confirmScoreService, NotificationSenderService notificationSender) {
        this.confirmScoreService = confirmScoreService;
        this.notificationSender = notificationSender;
    }

    @GetMapping("/{match_id}")
    public String confirmScoreSetByHost(@PathVariable(name = "match_id") final Long matchId,
                                        final Principal principal) {
        confirmScoreService.confirmScore(matchId, principal.getName());
        notificationSender.sendToGuest(matchId, NotificationType.SCORE_TO_SUBMIT);
        return "dashboard";
    }
}
