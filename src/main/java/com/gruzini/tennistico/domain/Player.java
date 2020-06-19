package com.gruzini.tennistico.domain;

import com.gruzini.tennistico.domain.enums.Gender;
import com.gruzini.tennistico.domain.enums.PlayerSkill;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;
import java.time.Period;

@Entity(name = "players")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Player {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;

    @NotNull
    private String firstName;

    @NotNull
    private String lastName;

    @Enumerated(EnumType.STRING)
    private Gender gender;

    private LocalDate birthday;

    private Integer yearsOfExperience;

    @Enumerated(EnumType.STRING)
    private PlayerSkill playerSkill;

    @Column(name = "ranking_points")
    private Integer rankingPoints;

    public Integer getAge() {
        return Period.between(getBirthday(), LocalDate.now()).getYears();
    }
}
