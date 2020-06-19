package com.gruzini.tennistico.domain;

import com.gruzini.tennistico.domain.enums.MatchStatus;
import com.gruzini.tennistico.exceptions.MatchAlreadyHasHostException;
import com.gruzini.tennistico.exceptions.MatchPlayersException;
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
    private static final int GUEST_INDEX = 1;

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

    @ManyToMany(fetch = FetchType.EAGER)
    @Getter(AccessLevel.PRIVATE)
    @Setter(AccessLevel.PRIVATE)
    private List<Player> players;

    @OneToOne
    @JoinColumn(name = "score_id")
    private Score score;

    public Player getHost() {
        return this.players.get(HOST_INDEX);
    }

    public Optional<Player> getGuest() {
        return Optional.ofNullable(this.players.get(GUEST_INDEX));
    }

    public void setHost(final Player player) {
        if (!this.players.isEmpty()) {
            throw new MatchAlreadyHasHostException();
        }
        players.add(player);
    }

    public void setGuest(final Player player) {
        if (this.players.size() != 1) {
            throw new MatchPlayersException();
        }
        players.add(player);
    }
}
