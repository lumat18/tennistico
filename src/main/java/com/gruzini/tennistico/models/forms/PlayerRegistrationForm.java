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

    @Enumerated(EnumType.STRING)
    private Gender gender;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @Past(message = "You cannot be born in future")
    private LocalDate birthday;

    @Min(0)
    @Max(99)
    private Integer yearsOfExperience;

    @Enumerated(EnumType.STRING)
    private PlayerSkill playerSkill;
}
