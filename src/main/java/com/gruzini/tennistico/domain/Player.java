package com.gruzini.tennistico.domain;

import com.gruzini.tennistico.enums.Gender;
import com.gruzini.tennistico.enums.PlayerSkill;
import lombok.*;
import org.hibernate.validator.constraints.Length;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;
import java.util.List;

@Entity(name = "players")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Player {
   @Id
   private Long id;

   @NotNull
   private String firstName;

   @NotNull
   private String lastName;

   @Enumerated(EnumType.STRING)
   private Gender gender;

   private LocalDate birthday;

   @Length(max = 1000)
   private String description;

   @Enumerated(EnumType.STRING)
   private PlayerSkill playerSkill;

//   unsure if it's going to be just a number or separate class consisting of several factors
//   private Integer rank;

   @OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
   @MapsId
   private User user;

   @ManyToMany(cascade = CascadeType.ALL)
   @JoinTable(name = "player_to_game",
           joinColumns = { @JoinColumn(name = "user_id") },
           inverseJoinColumns = { @JoinColumn(name = "game_id") })
   private List<Game> games;
}
