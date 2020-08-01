package com.gruzini.tennistico.bootstrap;

import com.gruzini.tennistico.domain.Court;
import com.gruzini.tennistico.domain.Match;
import com.gruzini.tennistico.domain.Player;
import com.gruzini.tennistico.domain.User;
import com.gruzini.tennistico.domain.enums.MatchStatus;
import com.gruzini.tennistico.services.entity_related.CourtService;
import com.gruzini.tennistico.services.entity_related.MatchService;
import com.gruzini.tennistico.services.entity_related.PlayerService;
import com.gruzini.tennistico.services.entity_related.UserService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Profile;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.List;

@Component
@Profile("dev")
@Order(3)
public class MatchInitializer implements CommandLineRunner {
   private final UserService userService;
   private final MatchService matchService;
   private final CourtService courtService;
   private final PlayerService playerService;

   public MatchInitializer(final UserService userService,
                           final MatchService matchService,
                           final CourtService courtService,
                           final PlayerService playerService) {
      this.userService = userService;
      this.matchService = matchService;
      this.courtService = courtService;
      this.playerService = playerService;
   }

   @Override
   public void run(final String... args) throws Exception {
      final Court court1 = courtService.getById(1L);
      final User user1 = userService.getByEmail("roger@user.pl");
      final User user2 = userService.getByEmail("nadal@user.pl");
      final User user3 = userService.getByEmail("user@user.pl");
      final Player player1 = playerService.getByUsername(user1.getEmail());
      final Player player2 = playerService.getByUsername(user2.getEmail());
      final Player player3 = playerService.getByUsername(user3.getEmail());

      final Match match1 = Match.builder()
              .court(court1)
              .startingAt(LocalDateTime.now())
              .endingAt(LocalDateTime.now().plusHours(2))
              .matchStatus(MatchStatus.UPCOMING)
              .players(List.of(player1, player2))
              .build();
      matchService.save(match1);
      final Match match2 = Match.builder()
              .court(court1)
              .startingAt(LocalDateTime.now())
              .endingAt(LocalDateTime.now().plusHours(2))
              .matchStatus(MatchStatus.UPCOMING)
              .players(List.of(player1, player3))
              .build();
      matchService.save(match2);
      final Match match3 = Match.builder()
              .court(court1)
              .startingAt(LocalDateTime.now().plusHours(24))
              .endingAt(LocalDateTime.now().plusHours(26))
              .matchStatus(MatchStatus.HOSTED)
              .players(List.of(player1))
              .build();
      matchService.save(match3);
      final Match match4 = Match.builder()
              .court(court1)
              .startingAt(LocalDateTime.now().plusHours(48))
              .endingAt(LocalDateTime.now().plusHours(50))
              .matchStatus(MatchStatus.UPCOMING)
              .players(List.of(player1, player2))
              .build();
      matchService.save(match4);
      final Match match5 = Match.builder()
              .court(court1)
              .startingAt(LocalDateTime.now().plusHours(72))
              .endingAt(LocalDateTime.now().plusHours(74))
              .matchStatus(MatchStatus.UPCOMING)
              .players(List.of(player2,player1))
              .build();
      matchService.save(match5);

   }
}
