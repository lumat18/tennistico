package com.gruzini.tennistico.domain;

import com.gruzini.tennistico.domain.enums.UserStatus;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;

@Entity(name = "users")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class User {
   @Id
   @GeneratedValue(strategy = GenerationType.IDENTITY)
   private Long id;

   @NotNull
   private String email;

   @NotNull
   private String password;

   private LocalDateTime createdAt;

   @Enumerated(EnumType.STRING)
   private UserStatus userStatus;

   @OneToOne(fetch = FetchType.LAZY)
   private Player player;
}
