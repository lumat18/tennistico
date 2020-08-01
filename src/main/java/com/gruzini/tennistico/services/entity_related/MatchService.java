package com.gruzini.tennistico.services.entity_related;

import com.gruzini.tennistico.domain.Match;
import com.gruzini.tennistico.domain.Player;
import com.gruzini.tennistico.domain.enums.MatchStatus;
import com.gruzini.tennistico.events.ChangeMatchStatusByEndingDateTimeEvent;
import com.gruzini.tennistico.exceptions.MatchNotFoundException;
import com.gruzini.tennistico.repositories.MatchRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import static java.lang.Math.abs;

@Service
@Transactional
public class MatchService {

    private final MatchRepository matchRepository;

    public MatchService(MatchRepository matchRepository) {
        this.matchRepository = matchRepository;
    }

    public Match save(final Match match) {
        return matchRepository.saveAndFlush(match);
    }

    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public Match getById(final Long id) {
        return matchRepository.findById(id).orElseThrow(MatchNotFoundException::new);
    }

    public List<Match> getFilteredHostedMatchesExceptHostedBy(final String filter, final Player player) {
        final List<Match> results = matchRepository.findAllByMatchStatusAndPlayersNotContainsAndStartingAtIsAfterOrderByStartingAt(MatchStatus.HOSTED, player, LocalDateTime.now());
        if (filter.equals("lvl")){
         return results.stream()
         .filter(match -> abs(match.getHost().getRankingPoints() - player.getRankingPoints()) > 500)
                 .collect(Collectors.toList());
        }
        return results;
    }

    public List<Match> getByPlayerAndStatus(final Player player, final MatchStatus matchStatus) {
        return matchRepository.getAllByPlayersContainsAndMatchStatus(player, matchStatus);
    }

    public List<Match> getByMatchStatusesAndPlayer(final List<MatchStatus> matchStatusList, final Player player) {
        return matchRepository.findAllByPlayersContainsAndStartingAtIsAfterAndMatchStatusInOrderByStartingAt(player, LocalDateTime.now(), matchStatusList);
    }

    public void updateExpiredMatchesStatusByStartingDateTime(final MatchStatus previousStatus, final MatchStatus desiredStatus) {
        List<Match> matchesToUpdate = getAllExpiredByStatus(previousStatus);
        updateMatchStatus(matchesToUpdate, desiredStatus);
    }

    public List<Match> getAllExpiredByStatus(final MatchStatus matchStatus) {
        return matchRepository.findByStartingAtBeforeAndMatchStatus(LocalDateTime.now(), matchStatus);
    }

    public void updateMatchStatus(final List<Match> matches, final MatchStatus matchStatus) {
        matches.forEach(match -> updateMatchStatus(match, matchStatus));
    }

    public void updateMatchStatus(final Match match, final MatchStatus matchStatus) {
        match.setMatchStatus(matchStatus);
        matchRepository.save(match);
    }

    public void updateExpiredMatchesStatusByEndingDateTime(final ChangeMatchStatusByEndingDateTimeEvent event) {
        List<Match> matchesToUpdate = getAllExpiredByStatusAndEndingDateTime(event.getCurrentMatchStatus());
        updateMatchStatus(matchesToUpdate, event.getDesiredMatchStatus());
    }

    private List<Match> getAllExpiredByStatusAndEndingDateTime(final MatchStatus matchStatus) {
        return matchRepository.findByEndingAtBeforeAndMatchStatus(LocalDateTime.now().minusDays(7), matchStatus);
    }

    public List<Player> getAllHostsByMatchStatus(final MatchStatus status){
        return getAllExpiredByStatus(status).stream()
                .map(Match::getHost)
                .collect(Collectors.toList());
    }

    public Optional<Match> getLastByPlayerAndStatus(Player player, MatchStatus status) {
        return matchRepository.getTopByPlayersContainsAndMatchStatusOrderByStartingAtDesc(player,status);
    }
}
