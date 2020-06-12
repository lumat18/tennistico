package com.gruzini.tennistico.bootstrap;

import com.gruzini.tennistico.domain.Court;
import com.gruzini.tennistico.domain.Game;
import com.gruzini.tennistico.domain.Player;
import com.gruzini.tennistico.domain.User;
import com.gruzini.tennistico.domain.enums.GameStatus;
import com.gruzini.tennistico.domain.enums.Gender;
import com.gruzini.tennistico.domain.enums.PlayerSkill;
import com.gruzini.tennistico.domain.enums.UserStatus;
import com.gruzini.tennistico.repositories.CourtRepository;
import com.gruzini.tennistico.repositories.GameRepository;
import com.gruzini.tennistico.repositories.PlayerRepository;
import com.gruzini.tennistico.repositories.UserRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.Month;
import java.util.List;

@Component
public class ArchivedGamesPageDataInitialization implements CommandLineRunner {
   private final CourtRepository courtRepository;
   private final GameRepository gameRepository;
   private final PlayerRepository playerRepository;
   private final UserRepository userRepository;
   private final PasswordEncoder passwordEncoder;

   public ArchivedGamesPageDataInitialization(final CourtRepository courtRepository,
                                              final GameRepository gameRepository,
                                              final PlayerRepository playerRepository,
                                              final UserRepository userRepository,
                                              final PasswordEncoder passwordEncoder) {
      this.courtRepository = courtRepository;
      this.gameRepository = gameRepository;
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
              .zipCode("69-420")
              .build();
      courtRepository.save(court);

      final Game game1 = Game.builder()
              .endingAt(LocalDateTime.of(2020, Month.JUNE, 1, 12, 10))
              .court(court)
              .gameStatus(GameStatus.PENDING)
              .score("3-2")
              .build();

      final Game game2 = Game.builder()
              .startingAt(LocalDateTime.of(2020, Month.JUNE, 1, 10, 10))
              .endingAt(LocalDateTime.of(2020, Month.JUNE, 1, 12, 10))
              .court(court)
              .gameStatus(GameStatus.UPCOMING)
              .score("3-2")
              .build();

      final Game game3 = Game.builder()
              .endingAt(LocalDateTime.of(2020, Month.JUNE, 5, 12, 10))
              .court(court)
              .gameStatus(GameStatus.SCORE_TO_BE_CONFIRMED)
              .score("3-2")
              .build();

      gameRepository.save(game1);
      gameRepository.save(game2);
      gameRepository.save(game3);

      final Player player1 = Player.builder()
              .firstName("Jimmy")
              .lastName("Jimmy")
              .gender(Gender.MALE)
              .playerSkill(PlayerSkill.BEGINNER)
              .yearsOfExperience(3)
              .birthday(LocalDate.of(2000, Month.AUGUST, 29))
              .games(List.of(game1, game2))
              .build();
      final Player player2 = Player.builder()
              .firstName("Sarah")
              .lastName("Sarah")
              .gender(Gender.FEMALE)
              .playerSkill(PlayerSkill.ADVANCED)
              .yearsOfExperience(10)
              .birthday(LocalDate.of(2001, Month.MARCH, 12))
              .games(List.of(game1, game2, game3))
              .build();
      final Player player3 = Player.builder()
              .firstName("Todd")
              .lastName("Todd")
              .playerSkill(PlayerSkill.BEGINNER)
              .gender(Gender.MALE)
              .yearsOfExperience(1)
              .birthday(LocalDate.of(1945, Month.APRIL, 30))
              .games(List.of(game3))
              .build();
      final User user1 = User.builder()
              .email("jimmy@test.pl")
              .createdAt(LocalDateTime.now())
              .password(passwordEncoder.encode("pass1"))
              .userStatus(UserStatus.ACTIVE)
              .build();
      final User user2 = User.builder()
              .email("sarah@test.pl")
              .createdAt(LocalDateTime.now())
              .password(passwordEncoder.encode("pass1"))
              .userStatus(UserStatus.ACTIVE)
              .build();
      final User user3 = User.builder()
              .email("todd@test.pl")
              .createdAt(LocalDateTime.now())
              .password(passwordEncoder.encode("pass1"))
              .userStatus(UserStatus.ACTIVE)
              .build();
      playerRepository.save(player1);
      playerRepository.save(player2);
      playerRepository.save(player3);
      user1.setPlayer(player1);
      user2.setPlayer(player2);
      user3.setPlayer(player3);
      userRepository.save(user1);
      userRepository.save(user2);
      userRepository.save(user3);
   }
}
