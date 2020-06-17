package com.gruzini.tennistico.controllers;

import com.gruzini.tennistico.domain.enums.NotificationType;
import com.gruzini.tennistico.exceptions.MatchNotFoundException;
import com.gruzini.tennistico.exceptions.PlayerNotFoundException;
import com.gruzini.tennistico.services.JoinMatchService;
import com.gruzini.tennistico.services.NotificationSenderService;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;

@Controller
@RequestMapping("/join-match")
public class JoinMatchController {

    private final JoinMatchService joinMatchService;
    private final NotificationSenderService notificationSender;

    public JoinMatchController(JoinMatchService joinMatchService, NotificationSenderService notificationSender) {
        this.joinMatchService = joinMatchService;
        this.notificationSender = notificationSender;
    }

    @PostMapping
    public String joinMatch(@RequestParam(name = "match_id") final Long matchId, final Principal principal) {
        joinMatchService.joinGuestToMatch(principal.getName(), matchId);
        notificationSender.sendToHost(matchId, NotificationType.JOIN_REQUEST);
        return "dashboard";
    }

    //TODO modify handlers according to general decision
    //TODO move handlers to controller advice
    @ExceptionHandler(MatchNotFoundException.class)
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public String handleMatchNotFoundException(final Exception exception, final Model model) {
        model.addAttribute("expMessage", exception.getMessage());
        return "error-information";
    }

    @ExceptionHandler(PlayerNotFoundException.class)
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public String handlePlayerNotFoundException(final Exception exception, final Model model){
        model.addAttribute("expMessage", exception.getMessage());
        return "error-information";
    }
}
