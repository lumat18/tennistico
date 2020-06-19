package com.gruzini.tennistico.repositories;

import com.gruzini.tennistico.domain.Match;
import com.gruzini.tennistico.domain.Player;
import com.gruzini.tennistico.domain.enums.MatchStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

public interface MatchRepository extends JpaRepository<Match, Long> {

    List<Match> findByStartingAtBeforeAndMatchStatus(LocalDateTime startingDateTime, MatchStatus matchStatus);

    List<Match> findByEndingAtBeforeAndMatchStatus(LocalDateTime endingDateTime, MatchStatus matchStatus);

    //metoda wyciaga mecze po statusie wg playera ktorego w meczu nie ma
    List<Match> findAllByMatchStatusAndAndHostNotAndStartingAtAfterOrderByStartingAt(MatchStatus status, Player player, LocalDateTime now);
    //List<Match> findAllByMatchStatusAndPlayersNotContainsAndStartingAtIsAfterOrderByStartingAt(MatchStatus status, Player player, LocalDateTime now);

    //metoda wyciaga mecze playera
    List<Match> findAllByHostOrGuestAndStartingAtAfterAndMatchStatusInOrderByStartingAt(Player player, Player player1, LocalDateTime now, List<MatchStatus> matchStatusList);
    //List<Match> findAllByPlayersContainsAndStartingAtIsAfterAndMatchStatusInOrderByStartingAt(Player player, LocalDateTime now, List<MatchStatus> matchStatusList);

    @Query("SELECT m FROM matches m WHERE m.host=:pl OR m.guest=:pl AND m.matchStatus=:status")
    List<Match> getAllByPlayerAndMatchStatus(@Param("pl") Player player, @Param("status") MatchStatus matchStatus);

    Optional<Match> findById(Long id);
}
