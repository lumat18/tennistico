package com.gruzini.tennistico.mappers;

import com.gruzini.tennistico.domain.Player;
import com.gruzini.tennistico.domain.enums.Gender;
import com.gruzini.tennistico.domain.enums.PlayerSkill;
import com.gruzini.tennistico.models.forms.PlayerRegistrationForm;
import org.springframework.stereotype.Component;

@Component
public class PlayerMapper {

    public Player toPlayer(final PlayerRegistrationForm registrationForm) {
        return Player.builder()
                .firstName(registrationForm.getFirstName())
                .lastName(registrationForm.getLastName())
                .birthday(registrationForm.getBirthday())
                .gender(Gender.valueOf(registrationForm.getGender()))
                .playerSkill(PlayerSkill.valueOf(registrationForm.getPlayerSkill()))
                .yearsOfExperience(registrationForm.getYearsOfExperience())
                .rankingPoints(PlayerSkill.valueOf(registrationForm.getPlayerSkill()).getRankingPoints())
                .build();
    }
}
