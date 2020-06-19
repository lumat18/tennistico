package com.gruzini.tennistico.services.dto_related;

import com.gruzini.tennistico.domain.Match;
import com.gruzini.tennistico.domain.User;
import com.gruzini.tennistico.domain.enums.MatchStatus;
import com.gruzini.tennistico.mappers.ArchivedMatchMapper;
import com.gruzini.tennistico.mappers.FutureMatchMapper;
import com.gruzini.tennistico.mappers.HostedMatchMapper;
import com.gruzini.tennistico.models.dto.matchDto.ArchivedMatchDto;
import com.gruzini.tennistico.models.dto.matchDto.FutureMatchDto;
import com.gruzini.tennistico.models.dto.matchDto.HostedMatchDto;
import com.gruzini.tennistico.services.entity_related.MatchService;
import com.gruzini.tennistico.services.entity_related.UserService;
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
    private final FutureMatchMapper futureMatchMapper;

    public MatchDtoService(MatchService matchService, UserService userService, HostedMatchMapper hostedMatchMapper, ArchivedMatchMapper archivedMatchMapper, final FutureMatchMapper futureMatchMapper) {
        this.matchService = matchService;
        this.userService = userService;
        this.hostedMatchMapper = hostedMatchMapper;
        this.archivedMatchMapper = archivedMatchMapper;
        this.futureMatchMapper = futureMatchMapper;
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

    public List<FutureMatchDto> getAllFutureMatchesPlayerIsInvolvedIn(final String username) {
        final User user = userService.getByEmail(username);
        return matchService.getByMatchStatusesAndHostedBy(List.of(MatchStatus.HOSTED, MatchStatus.JOIN_REQUEST, MatchStatus.UPCOMING), user.getPlayer()).stream()
                .map(futureMatchMapper::toFutureMatchDto)
                .collect(Collectors.toList());
    }
}
