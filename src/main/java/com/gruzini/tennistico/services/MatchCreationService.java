package com.gruzini.tennistico.services;

import com.gruzini.tennistico.domain.Match;
import com.gruzini.tennistico.domain.Player;
import com.gruzini.tennistico.domain.enums.NotificationType;
import com.gruzini.tennistico.mappers.CreatedMatchMapper;
import com.gruzini.tennistico.models.dto.CreatedMatchDto;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
@Slf4j
public class MatchCreationService {

    private final CreatedMatchMapper createdMatchMapper;
    private final MatchService matchService;
    private final PlayerService playerService;
    private final UserService userService;
    private final NotificationService notificationService;

    public MatchCreationService(CreatedMatchMapper createdMatchMapper, MatchService matchService, PlayerService playerService, UserService userService, NotificationService notificationService) {
        this.createdMatchMapper = createdMatchMapper;
        this.matchService = matchService;
        this.playerService = playerService;
        this.userService = userService;
        this.notificationService = notificationService;
    }

    public void create(final CreatedMatchDto createdMatchDto, final String username) {
        final Player player = userService.getByEmail(username).getPlayer();
        final Match createdMatch = saveCreatedMatch(createdMatchDto);
        playerService.add(player, createdMatch);
        sendNotificationToMatchHost(username);
    }

    private Match saveCreatedMatch(CreatedMatchDto createdMatchDto) {
        final Match createdMatch = createdMatchMapper.toMatch(createdMatchDto);
        matchService.save(createdMatch);
        return createdMatch;
    }

    private void sendNotificationToMatchHost(String username) {
        notificationService.createNotificationFor(userService.getByEmail(username).getPlayer(), NotificationType.MATCH_CREATED);
    }
}
