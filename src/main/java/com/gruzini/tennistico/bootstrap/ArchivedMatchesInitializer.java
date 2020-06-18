package com.gruzini.tennistico.bootstrap;

import com.gruzini.tennistico.domain.Court;
import com.gruzini.tennistico.domain.Match;
import com.gruzini.tennistico.domain.Player;
import com.gruzini.tennistico.domain.User;
import com.gruzini.tennistico.domain.enums.Gender;
import com.gruzini.tennistico.domain.enums.MatchStatus;
import com.gruzini.tennistico.domain.enums.PlayerSkill;
import com.gruzini.tennistico.domain.enums.UserStatus;
import com.gruzini.tennistico.repositories.CourtRepository;
import com.gruzini.tennistico.repositories.MatchRepository;
import com.gruzini.tennistico.repositories.PlayerRepository;
import com.gruzini.tennistico.repositories.UserRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Profile;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.Month;
import java.util.List;

@Component
@Profile("dev")
public class ArchivedMatchesInitializer implements CommandLineRunner {
   private final CourtRepository courtRepository;
   private final MatchRepository matchRepository;
   private final PlayerRepository playerRepository;
   private final UserRepository userRepository;
   private final PasswordEncoder passwordEncoder;

   public ArchivedMatchesInitializer(final CourtRepository courtRepository,
                                     final MatchRepository matchRepository,
                                     final PlayerRepository playerRepository,
                                     final UserRepository userRepository,
                                     final PasswordEncoder passwordEncoder) {
      this.courtRepository = courtRepository;
      this.matchRepository = matchRepository;
      this.playerRepository = playerRepository;
      this.userRepository = userRepository;
      this.passwordEncoder = passwordEncoder;
   }

   @Override
   public void run(final String... args) throws Exception {
      final Court court = Court.builder()
              .name("Nice court")
              .street("Nice")
              .city("Glad")
              .state("Liquid")
              .country("Utopia")
              .phoneNumber("+48 666 66 66")
              .zipCode("69-420")
              .build();
      courtRepository.save(court);

      final Match match1 = Match.builder()
              .startingAt(LocalDateTime.of(2020, Month.JUNE, 1, 10, 10))
              .endingAt(LocalDateTime.of(2020, Month.JUNE, 1, 12, 10))
              .court(court)
              .matchStatus(MatchStatus.PENDING)
              .score("3-2")
              .build();

      final Match match2 = Match.builder()
              .startingAt(LocalDateTime.of(2020, Month.JUNE, 21, 10, 10))
              .endingAt(LocalDateTime.of(2020, Month.JUNE, 21, 12, 10))
              .court(court)
              .matchStatus(MatchStatus.UPCOMING)
              .score("3-2")
              .build();

      final Match match3 = Match.builder()
              .startingAt(LocalDateTime.of(2020, Month.JUNE, 5, 10, 10))
              .endingAt(LocalDateTime.of(2020, Month.JUNE, 5, 12, 10))
              .court(court)
              .matchStatus(MatchStatus.SCORE_TO_BE_CONFIRMED)
              .score("3-2")
              .build();

      final Match match4 = Match.builder()
              .startingAt(LocalDateTime.of(2020, Month.JUNE, 19, 10, 10))
              .endingAt(LocalDateTime.of(2020, Month.JUNE, 19, 12, 10))
              .court(court)
              .matchStatus(MatchStatus.HOSTED)
              .score("3-2")
              .build();

      final Match match5 = Match.builder()
              .startingAt(LocalDateTime.of(2020, Month.JUNE, 20, 10, 10))
              .endingAt(LocalDateTime.of(2020, Month.JUNE, 20, 12, 10))
              .court(court)
              .matchStatus(MatchStatus.JOIN_REQUEST)
              .score("3-2")
              .build();

      matchRepository.saveAll(List.of(match1, match2, match3, match4, match5));

      final Player player1 = Player.builder()
              .firstName("Jimmy")
              .lastName("Jimmy")
              .gender(Gender.MALE)
              .playerSkill(PlayerSkill.BEGINNER)
              .yearsOfExperience(3)
              .birthday(LocalDate.of(2000, Month.AUGUST, 29))
              .matches(List.of(match1, match2, match4, match5))
              .build();
      final Player player2 = Player.builder()
              .firstName("Sarah")
              .lastName("Sarah")
              .gender(Gender.FEMALE)
              .playerSkill(PlayerSkill.ADVANCED)
              .yearsOfExperience(10)
              .birthday(LocalDate.of(2001, Month.MARCH, 12))
              .matches(List.of(match1, match2, match3))
              .build();
      final Player player3 = Player.builder()
              .firstName("Todd")
              .lastName("Todd")
              .playerSkill(PlayerSkill.BEGINNER)
              .gender(Gender.MALE)
              .yearsOfExperience(1)
              .birthday(LocalDate.of(1945, Month.APRIL, 30))
              .matches(List.of(match3))
              .build();

      playerRepository.saveAll(List.of(player1, player2, player3));

      final User user1 = User.builder()
              .email("jimmy@test.pl")
              .createdAt(LocalDateTime.now())
              .password(passwordEncoder.encode("pass1"))
              .userStatus(UserStatus.ACTIVE)
              .player(player1)
              .build();
      final User user2 = User.builder()
              .email("sarah@test.pl")
              .createdAt(LocalDateTime.now())
              .password(passwordEncoder.encode("pass1"))
              .userStatus(UserStatus.ACTIVE)
              .player(player2)
              .build();
      final User user3 = User.builder()
              .email("todd@test.pl")
              .createdAt(LocalDateTime.now())
              .password(passwordEncoder.encode("pass1"))
              .userStatus(UserStatus.ACTIVE)
              .player(player3)
              .build();

      userRepository.saveAll(List.of(user1, user2, user3));
   }
}
