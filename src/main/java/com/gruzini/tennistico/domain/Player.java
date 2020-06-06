package com.gruzini.tennistico.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Length;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;

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

   private String gender;

   private LocalDate birthday;

   private Integer age;

   @Length(max = 1000)
   private String description;

//   unsure if it's going to be just a number or separate class consisting of several factors
//   private Integer rank;

   @OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
   @MapsId
   private User user;
}
