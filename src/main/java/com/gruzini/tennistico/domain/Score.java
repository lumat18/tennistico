package com.gruzini.tennistico.domain;

import lombok.*;

import javax.persistence.*;
import java.util.List;

@Entity(name = "scores")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Score {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;

    @OneToMany
    private List<Set> sets;

    @ManyToOne
    private Player winner;

    @ManyToOne
    private Player loser;

}
