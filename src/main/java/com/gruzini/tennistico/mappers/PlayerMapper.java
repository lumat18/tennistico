package com.gruzini.tennistico.mappers;

import com.gruzini.tennistico.domain.Player;
import com.gruzini.tennistico.forms.PlayerRegistrationForm;
import org.springframework.stereotype.Component;

@Component
public class PlayerMapper {

    public Player toPlayer(final PlayerRegistrationForm registrationForm) {
        return Player.builder()
                .firstName(registrationForm.getFirstName())
                .lastName(registrationForm.getLastName())
                .birthday(registrationForm.getBirthday())
                .gender(registrationForm.getGender())
                .playerSkill(registrationForm.getPlayerSkill())
                .yearsOfExperience(registrationForm.getYearsOfExperience())
                .build();
    }
}
