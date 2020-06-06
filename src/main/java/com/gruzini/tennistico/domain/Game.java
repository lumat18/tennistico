package com.gruzini.tennistico.domain;

import com.gruzini.tennistico.enums.GameStatus;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.time.LocalDateTime;
import java.util.List;

@Entity(name = "games")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Game {
   @Id
   @GeneratedValue
   private Long id;

   private boolean isOpen;

   private LocalDateTime createdAt;

   private GameStatus gameStatus;

//   private Court location;

//   private Player hostPlayer;

//   private List<Player> joiningPlayers;
}
