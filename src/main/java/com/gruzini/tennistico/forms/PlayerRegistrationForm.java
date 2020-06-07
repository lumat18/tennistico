package com.gruzini.tennistico.forms;

import com.gruzini.tennistico.enums.Gender;
import com.gruzini.tennistico.enums.PlayerSkill;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
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
    private String firstName;

    @NotNull
    private String lastName;

    @Enumerated(EnumType.STRING)
    private Gender gender;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @Past
    private LocalDate birthday;

    @Min(0)
    @Max(99)
    private Integer yearsOfExperience;

    @Enumerated(EnumType.STRING)
    private PlayerSkill playerSkill;
}
