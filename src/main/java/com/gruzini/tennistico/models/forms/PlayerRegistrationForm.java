package com.gruzini.tennistico.models.forms;

import com.gruzini.tennistico.domain.enums.Gender;
import com.gruzini.tennistico.domain.enums.PlayerSkill;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Length;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Past;
import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class PlayerRegistrationForm {

    @NotNull
    @Length(min = 1, message = "First name cannot be empty")
    private String firstName;

    @NotNull
    @Length(min = 1, message = "Last name cannot be empty")
    private String lastName;

    @NotNull(message = "You have to pick gender")
    private Gender gender;

    @NotNull(message = "You have to provide your date of birth")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @Past(message = "You cannot be born in future")
    private LocalDate birthday;

    @NotNull(message = "You have to provide number of years you have played")
    @Min(value = 0, message = "You must provide a number ranging from 0 to 99")
    @Max(value = 99, message = "You must provide a number ranging from 0 to 99")
    private Integer yearsOfExperience;

    @NotNull(message = "You have to pick your level of skill")
    private PlayerSkill playerSkill;
}
