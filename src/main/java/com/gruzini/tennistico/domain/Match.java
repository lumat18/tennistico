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

    private static final int HOST_INDEX = 0;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "match_id")
    private Long id;

    private boolean isOpen;

    private LocalDateTime startingAt;

    private LocalDateTime endingAt;

    private String score;

    @Enumerated(EnumType.STRING)
    private MatchStatus matchStatus;

    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    @JoinColumn(name = "court_id")
    private Court court;

    @ManyToMany(mappedBy = "matches", fetch = FetchType.EAGER)
    @EqualsAndHashCode.Exclude
    private List<Player> players;

    public Player getHost() {
        return players.get(HOST_INDEX);
    }

    public Optional<Player> getGuest() {
        return Optional.ofNullable(players.get(1));
    }
}
