package com.gruzini.tennistico.services;

import com.gruzini.tennistico.domain.Match;
import com.gruzini.tennistico.domain.User;
import com.gruzini.tennistico.domain.enums.MatchStatus;
import com.gruzini.tennistico.mappers.ArchivedMatchMapper;
import com.gruzini.tennistico.mappers.HostedMatchMapper;
import com.gruzini.tennistico.models.dto.ArchivedMatchDto;
import com.gruzini.tennistico.models.dto.HostedMatchDto;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
public class MatchDtoService {

    private final MatchService matchService;
    private final UserService userService;
    private final HostedMatchMapper hostedMatchMapper;
    private final ArchivedMatchMapper archivedMatchMapper;

    public MatchDtoService(MatchService matchService, UserService userService, HostedMatchMapper hostedMatchMapper, ArchivedMatchMapper archivedMatchMapper) {
        this.matchService = matchService;
        this.userService = userService;
        this.hostedMatchMapper = hostedMatchMapper;
        this.archivedMatchMapper = archivedMatchMapper;
    }

    public List<HostedMatchDto> getHostedMatchesDtoExceptHostedBy(final String username) {
        final User user = userService.getByEmail(username);
        final List<Match> hostedNotByPlayer = matchService.getHostedMatchesExceptHostedBy(user.getPlayer());
        return hostedNotByPlayer.stream()
                .map(hostedMatchMapper::toMatchInfoDto)
                .collect(Collectors.toList());
    }

    public List<ArchivedMatchDto> getArchiveMatchDtoBy(final String username) {
        final User user = userService.getByEmail(username);
        return matchService.getByPlayerAndStatus(user.getPlayer(), MatchStatus.ARCHIVED).stream()
                .map(match -> archivedMatchMapper.toArchivedMatchDTO(match, user.getPlayer()))
                .collect(Collectors.toList());
    }

    public List<HostedMatchDto> getAllFutureMatchesPlayerIsInvolvedIn(final String username) {
        final User user = userService.getByEmail(username);
        return null;
    }
}
