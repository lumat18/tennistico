package com.gruzini.tennistico.controllers;

import com.gruzini.tennistico.events.ConfirmScoreEvent;
import com.gruzini.tennistico.mappers.MatchInfoParser;
import com.gruzini.tennistico.services.entity_related.PlayerService;
import com.gruzini.tennistico.services.notifications.NotificationService;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;

@Controller
@RequestMapping("/confirm-score")
public class ConfirmScoreController {

    private final ApplicationEventPublisher applicationEventPublisher;
    private final NotificationService notificationService;
    private final MatchInfoParser matchInfoParser;
    private final PlayerService playerService;

    public ConfirmScoreController(ApplicationEventPublisher applicationEventPublisher, NotificationService notificationService, MatchInfoParser matchInfoParser, PlayerService playerService) {
        this.applicationEventPublisher = applicationEventPublisher;
        this.notificationService = notificationService;
        this.matchInfoParser = matchInfoParser;
        this.playerService = playerService;
    }

    @PostMapping
    public String confirmScoreSetByHost(@RequestParam(name = "match_id") final Long matchId,
                                        @RequestParam(name = "trigger_id") final Long triggerNotificationId,
                                        final Principal principal,
                                        final Model model) {

        final boolean existsNewerConfirmNotification = notificationService.existsNewerConfirmNotification(matchId, triggerNotificationId);
        System.out.println("passed id" + triggerNotificationId);
        if (existsNewerConfirmNotification){
            model.addAttribute("message", "Apparently, your opponent has edited the score, confirm if it is correct. New score is:");
            model.addAttribute("newScore", matchInfoParser.getScore(matchId, playerService.getByUsername(principal.getName())));
            model.addAttribute("matchId", matchId);
            model.addAttribute("notificationId", notificationService.getIdOfNewestConfirmNotification(matchId));
            System.out.println("newest id" + notificationService.getIdOfNewestConfirmNotification(matchId));
            return "newer-score";
        }
        applicationEventPublisher.publishEvent(new ConfirmScoreEvent(this, matchId, triggerNotificationId, principal.getName()));
        return "redirect:/dashboard";
    }
}
