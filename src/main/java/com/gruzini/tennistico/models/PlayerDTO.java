package com.gruzini.tennistico.models;

import com.gruzini.tennistico.enums.Gender;
import com.gruzini.tennistico.enums.PlayerSkill;
import lombok.Data;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Past;
import java.time.LocalDate;

@Data
public class PlayerDTO {
    @NotNull
    @Length(min = 3)
    private String firstName;
    @NotNull
    @Length(min = 2)
    private String lastName;
    @NotNull
    private Gender gender;
    @Past
    private LocalDate birthday;
    @Length(max = 1000)
    private String description;
    private PlayerSkill playerSkill;

}
