package com.gruzini.tennistico.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Entity(name = "courts")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Court {
   @Id
   @GeneratedValue(strategy = GenerationType.IDENTITY)
   @Column(name = "court_id")
   private Long id;

   @NotNull
   private String name;

   private String street;

   @NotNull
   private String city;

   private String state;

   private String country;

   private String zipCode;

   private String phoneNumber;
}
