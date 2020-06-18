package com.gruzini.tennistico.domain;

import com.gruzini.tennistico.domain.enums.MatchStatus;
import lombok.*;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Entity(name = "matches")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Match {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "match_id")
    private Long id;

    private LocalDateTime startingAt;

    private LocalDateTime endingAt;

    @Enumerated(EnumType.STRING)
    private MatchStatus matchStatus;

    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    @JoinColumn(name = "court_id")
    private Court court;

    @ManyToOne
    private Player host;

    @ManyToOne
    private Player guest;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "score_id")
    private Score score;

    public Optional<Player> getGuest() {
        return Optional.ofNullable(guest);
    }
}
