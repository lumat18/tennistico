package com.gruzini.tennistico.domain;

import com.gruzini.tennistico.enums.GameStatus;
import lombok.*;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.List;

@Entity(name = "games")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Game {
   @Id
   @GeneratedValue
   @Column(name = "game_id")
   private Long id;

   private boolean isOpen;

   private LocalDateTime startingAt;

   private LocalDateTime endingAt;

   private GameStatus gameStatus;

   @ManyToOne(optional = false, fetch = FetchType.LAZY)
   @JoinColumn(name = "court_id")
   private Court court;

   @ManyToMany(mappedBy = "games")
   @ToString.Exclude
   @EqualsAndHashCode.Exclude
   private List<Player> players;
}