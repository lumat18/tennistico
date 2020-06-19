package com.gruzini.tennistico.repositories;

import com.gruzini.tennistico.domain.Match;
import com.gruzini.tennistico.domain.Player;
import com.gruzini.tennistico.domain.enums.MatchStatus;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

public interface MatchRepository extends JpaRepository<Match, Long> {

    List<Match> findByStartingAtBeforeAndMatchStatus(LocalDateTime startingDateTime, MatchStatus matchStatus);

    List<Match> findByEndingAtBeforeAndMatchStatus(LocalDateTime endingDateTime, MatchStatus matchStatus);

    List<Match> findAllByMatchStatusAndPlayersNotContainsAndStartingAtIsAfterOrderByStartingAt(MatchStatus status, Player player, LocalDateTime now);

    List<Match> findAllByPlayersContainsAndStartingAtIsAfterAndMatchStatusInOrderByStartingAt(Player player, LocalDateTime now, List<MatchStatus> matchStatusList);

    List<Match> getAllByPlayersContainsAndMatchStatus(Player player, MatchStatus matchStatus);

    Optional<Match> findById(Long id);
}
