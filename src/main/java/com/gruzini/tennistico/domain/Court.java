package com.gruzini.tennistico.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Entity(name = "courts")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Court {
   @Id
   @GeneratedValue(strategy = GenerationType.IDENTITY)
   @Column(name = "court_id")
   private Long id;

   @NotNull
   private String street;

   @NotNull
   private String city;

   @NotNull
   private String state;

   @NotNull
   private String country;

   @NotNull
   private String zipCode;
}
