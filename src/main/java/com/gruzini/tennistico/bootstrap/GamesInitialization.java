package com.gruzini.tennistico.bootstrap;

import com.gruzini.tennistico.domain.Court;
import com.gruzini.tennistico.domain.Game;
import com.gruzini.tennistico.domain.Player;
import com.gruzini.tennistico.domain.enums.GameStatus;
import com.gruzini.tennistico.domain.enums.Gender;
import com.gruzini.tennistico.domain.enums.PlayerSkill;
import com.gruzini.tennistico.repositories.CourtRepository;
import com.gruzini.tennistico.repositories.GameRepository;
import com.gruzini.tennistico.repositories.PlayerRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Component
public class GamesInitialization implements CommandLineRunner {
    private final PlayerRepository playerRepository;
    private final CourtRepository courtRepository;
    private final GameRepository gameRepository;

    public GamesInitialization(PlayerRepository playerRepository, CourtRepository courtRepository, GameRepository gameRepository) {
        this.playerRepository = playerRepository;
        this.courtRepository = courtRepository;
        this.gameRepository = gameRepository;
    }

    @Override
    public void run(String... args) throws Exception {
        dataInit();
    }

     void dataInit() {
         final Court court = Court.builder()
                 .city("Madrid")
                 .country("Spain")
                 .state("Castilla")
                 .street("Calle")
                 .zipCode("00-000")
                 .build();
         courtRepository.save(court);

         final Game game1 = Game.builder()
                 .court(court)
                 .startingAt(LocalDateTime.of(2020, 6, 20, 8, 30))
                 .endingAt(LocalDateTime.of(2020, 6, 20, 11, 30))
                 .gameStatus(GameStatus.UPCOMING)
                 .isOpen(true)
                 .build();
         final Game game2 = Game.builder()
                 .court(court)
                 .startingAt(LocalDateTime.of(2020, 6, 22, 10, 0))
                 .endingAt(LocalDateTime.of(2020, 6, 22, 13, 0))
                 .gameStatus(GameStatus.UPCOMING)
                 .isOpen(true)
                 .build();
         final Game game3 = Game.builder()
                 .court(court)
                 .startingAt(LocalDateTime.of(2020, 6, 28, 13, 0))
                 .endingAt(LocalDateTime.of(2020, 6, 28, 16, 0))
                 .gameStatus(GameStatus.UPCOMING)
                 .isOpen(true)
                 .build();

        final Player player1 = Player.builder()
                .firstName("Roger")
                .lastName("Federer")
                .birthday(LocalDate.of(1981, 8, 8))
                .gender(Gender.MALE)
                .playerSkill(PlayerSkill.PROFESSIONAL)
                .yearsOfExperience(32)
                .games(List.of(game1, game3))
                .build();

        final Player player2 = Player.builder()
                .firstName("Rafael")
                .lastName("Nadal")
                .birthday(LocalDate.of(1986, 6, 3))
                .gender(Gender.MALE)
                .playerSkill(PlayerSkill.PROFESSIONAL)
                .yearsOfExperience(31)
                .games(List.of(game2))
                .build();
        playerRepository.save(player1);
        playerRepository.save(player2);

    }
}
