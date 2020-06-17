package com.gruzini.tennistico.domain;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Data
@NoArgsConstructor
@Entity(name = "rankings")
public class Ranking {
    @Id
    @GeneratedValue
    private Long id;

    private int wins;
    private int losses;
    private int RankingPoints;
}
