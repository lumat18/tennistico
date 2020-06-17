package com.gruzini.tennistico.controllers;

import com.gruzini.tennistico.domain.enums.NotificationType;
import com.gruzini.tennistico.services.ConfirmJoinService;
import com.gruzini.tennistico.services.NotificationSenderService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.security.Principal;

@Controller
@RequestMapping("/confirm-join")
public class ConfirmJoinController {

    private final ConfirmJoinService confirmJoinService;
    private final NotificationSenderService notificationSender;

    public ConfirmJoinController(ConfirmJoinService confirmJoinService, NotificationSenderService notificationSender) {
        this.confirmJoinService = confirmJoinService;
        this.notificationSender = notificationSender;
    }

    @PostMapping
    public String confirmGuestJoiningMatch(@RequestParam(name = "match_id") final Long matchId, final Principal principal) {
        confirmJoinService.confirmJoin(matchId, principal.getName());
        notificationSender.sendToGuest(matchId, NotificationType.JOIN_CONFIRMED);
        return "dashboard";
    }
}
