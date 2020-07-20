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
@Order(4)
public class PendingMatchInitializer implements CommandLineRunner {
   private final UserService userService;
   private final MatchService matchService;
   private final CourtService courtService;
   private final PlayerService playerService;

   public PendingMatchInitializer(final UserService userService,
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
      final Court court = courtService.getById(1L);
      final User user1 = userService.getByEmail("roger@user.pl");
      final User user2 = userService.getByEmail("nadal@user.pl");
      final User user3 = userService.getByEmail("user@user.pl");
      final Player player1 = playerService.getByUsername(user1.getEmail());
      final Player player2 = playerService.getByUsername(user2.getEmail());
      final Player player3 = playerService.getByUsername(user3.getEmail());
      final Match match1 = Match.builder()
              .court(court)
              .startingAt(LocalDateTime.now())
              .endingAt(LocalDateTime.now().plusHours(2))
              .matchStatus(MatchStatus.UPCOMING)
              .players(List.of(player1,player2))
              .build();
      final Match match2 = Match.builder()
              .court(court)
              .startingAt(LocalDateTime.now())
              .endingAt(LocalDateTime.now().plusHours(2))
              .matchStatus(MatchStatus.UPCOMING)
              .players(List.of(player1,player3))
              .build();
      matchService.save(match1);
      matchService.save(match2);
   }
}
