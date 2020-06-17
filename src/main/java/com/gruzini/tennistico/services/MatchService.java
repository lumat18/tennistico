package com.gruzini.tennistico.services;

import com.gruzini.tennistico.domain.Match;
import com.gruzini.tennistico.domain.Player;
import com.gruzini.tennistico.domain.enums.MatchStatus;
import com.gruzini.tennistico.events.ChangeMatchStatusByEndingDateTimeEvent;
import com.gruzini.tennistico.events.ChangeMatchStatusByStartingDateTimeEvent;
import com.gruzini.tennistico.exceptions.MatchNotFoundException;
import com.gruzini.tennistico.repositories.MatchRepository;
import com.gruzini.tennistico.repositories.PlayerRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

@Service
@Transactional
public class MatchService {

    private final MatchRepository matchRepository;
    private final PlayerRepository playerRepository;

    public MatchService(MatchRepository matchRepository, PlayerRepository playerRepository) {
        this.matchRepository = matchRepository;
        this.playerRepository = playerRepository;
    }

    public Match save(final Match match) {
        return matchRepository.saveAndFlush(match);
    }

    public void updateExpiredMatchesStatusByStartingDateTime(final ChangeMatchStatusByStartingDateTimeEvent event) {
        List<Match> matchesToUpdate = getAllExpiredByStatusAndStartingDateTime(event.getCurrentMatchStatus());
        updateMatchStatus(matchesToUpdate, event.getDesiredMatchStatus());
    }

    private List<Match> getAllExpiredByStatusAndStartingDateTime(final MatchStatus matchStatus) {
        return matchRepository.findByStartingAtBeforeAndMatchStatus(LocalDateTime.now(), matchStatus);
    }

    public void updateMatchStatus(final List<Match> matches, final MatchStatus matchStatus) {
        matches.forEach(match -> updateMatchStatus(match, matchStatus));
    }

    public void updateExpiredMatchesStatusByEndingDateTime(final ChangeMatchStatusByEndingDateTimeEvent event) {
        List<Match> matchesToUpdate = getAllExpiredByStatusAndEndingDateTime(event.getCurrentMatchStatus());
        updateMatchStatus(matchesToUpdate, event.getDesiredMatchStatus());
    }

    private List<Match> getAllExpiredByStatusAndEndingDateTime(final MatchStatus matchStatus) {
        return matchRepository.findByEndingAtBeforeAndMatchStatus(LocalDateTime.now().minusDays(7), matchStatus);
    }

    public void updateMatchStatus(final Match match, final MatchStatus matchStatus) {
        match.setMatchStatus(matchStatus);
        matchRepository.save(match);
        match.getPlayers().forEach(playerRepository::save);
    }

    public void updateMatchScore(final Match match, final String score) {
        match.setScore(score);
        save(match);
    }

    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public Match getById(final Long id) {
        return matchRepository.findById(id).orElseThrow(MatchNotFoundException::new);
    }

    public List<Match> getHostedMatchesExceptHostedBy(final Player player) {
        return matchRepository.findAllByMatchStatusAndPlayersNotContainsAndStartingAtIsAfterOrderByStartingAt(MatchStatus.HOSTED, player, LocalDateTime.now());
    }

    public List<Match> getByPlayerAndStatus(final Player player, final MatchStatus matchStatus) {
        return matchRepository.getAllByPlayersAndMatchStatus(player, matchStatus);
    }
}
