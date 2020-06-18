package com.gruzini.tennistico.domain;

import com.gruzini.tennistico.domain.enums.MatchStatus;
import com.gruzini.tennistico.exceptions.PlayerNotFoundException;
import lombok.*;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.List;

@Entity(name = "matches")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Match {

    private static final int HOST_INDEX = 0;
    private static final int GUEST_INDEX = 1;

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

    public Player getGuest() {
        if (players.get(GUEST_INDEX) != null) {
            return players.get(GUEST_INDEX);
        } else {
            throw new PlayerNotFoundException();
        }
    }
}
