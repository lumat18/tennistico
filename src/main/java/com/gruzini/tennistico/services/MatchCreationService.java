package com.gruzini.tennistico.services;

import com.gruzini.tennistico.domain.Match;
import com.gruzini.tennistico.domain.Player;
import com.gruzini.tennistico.mappers.CreatedMatchMapper;
import com.gruzini.tennistico.models.dto.matchDto.CreatedMatchDto;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class MatchCreationService {

    private final CreatedMatchMapper createdMatchMapper;
    private final MatchService matchService;
    private final UserService userService;

    public MatchCreationService(CreatedMatchMapper createdMatchMapper, MatchService matchService, UserService userService) {
        this.createdMatchMapper = createdMatchMapper;
        this.matchService = matchService;
        this.userService = userService;
    }

    public Match create(final CreatedMatchDto createdMatchDto, final String username) {
        final Player player = userService.getByEmail(username).getPlayer();
        final Match createdMatch = createdMatchMapper.toMatch(createdMatchDto);
        createdMatch.setHost(player);
        return matchService.save(createdMatch);
    }
}
