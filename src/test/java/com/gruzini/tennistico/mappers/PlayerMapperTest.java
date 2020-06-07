package com.gruzini.tennistico.mappers;

import com.gruzini.tennistico.domain.Player;
import com.gruzini.tennistico.enums.Gender;
import com.gruzini.tennistico.enums.PlayerSkill;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.time.Period;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;

class PlayerMapperTest {
    private PlayerMapper playerMapper;

    @BeforeEach
    void setUp() {
        playerMapper = new PlayerMapper();
    }

    @Test
    void shouldCalculateAgeProperly() {
        //given
        final LocalDate birthday = LocalDate.of(2000, 1, 1);
        final Player player = new Player();
        player.setBirthday(birthday);
        final int expectedAge = Period.between(birthday, LocalDate.now()).getYears();
        //when
        final PlayerDTO playerDTO = playerMapper.mapPlayerToPlayerDTO(player);
        final Integer age = playerDTO.getAge();
        //then
        assertEquals(expectedAge, age);
    }

    @Test
    void shouldCalculateAgeAsZeroWhenBirthdayIsNull() {
        //given
        final Player player = new Player();
        player.setBirthday(null);
        //when
        final PlayerDTO playerDTO = playerMapper.mapPlayerToPlayerDTO(player);
        final Integer age = playerDTO.getAge();
        //then
        assertEquals(0, age);
    }

    @Test
    void shouldMapPlayerToPlayerDTO() {
        //given
        final Player player = Player.builder()
                .firstName("firstName")
                .lastName("lastName")
                .birthday(LocalDate.of(2000, 1, 1))
                .description("sample description")
                .gender(Gender.MALE)
                .playerSkill(PlayerSkill.BEGINNER)
                .build();
        //when
        final PlayerDTO playerDTO = playerMapper.mapPlayerToPlayerDTO(player);
        //then
        assertAll(() -> {
            assertEquals(player.getFirstName(), playerDTO.getFirstName());
            assertEquals(player.getLastName(), playerDTO.getLastName());
            assertEquals(player.getBirthday(), playerDTO.getBirthday());
            assertEquals(player.getDescription(), playerDTO.getDescription());
            assertEquals(player.getGender(), playerDTO.getGender());
            assertEquals(player.getPlayerSkill(), playerDTO.getPlayerSkill());
        });
    }

    @Test
    void shouldMapPlayerDTOtoPlayer() {
        //given
        final PlayerDTO playerDTO = PlayerDTO.builder()
                .firstName("firstName")
                .lastName("lastName")
                .birthday(LocalDate.of(2000, 1, 1))
                .description("sample description")
                .gender(Gender.MALE)
                .playerSkill(PlayerSkill.BEGINNER)
                .build();
        //when
        final Player player = playerMapper.mapPlayerDTOtoPlayer(playerDTO);
        //then
        assertAll(() -> {
            assertEquals(playerDTO.getFirstName(), player.getFirstName());
            assertEquals(playerDTO.getLastName(), player.getLastName());
            assertEquals(playerDTO.getBirthday(), player.getBirthday());
            assertEquals(playerDTO.getDescription(), player.getDescription());
            assertEquals(playerDTO.getGender(), player.getGender());
            assertEquals(playerDTO.getPlayerSkill(), player.getPlayerSkill());
        });
    }
}