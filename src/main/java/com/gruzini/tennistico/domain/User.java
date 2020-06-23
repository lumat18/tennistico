package com.gruzini.tennistico.domain;

import com.gruzini.tennistico.domain.enums.UserStatus;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;

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

   @CreationTimestamp
   @Column(name = "created_at")
   private LocalDateTime createdAt;

   @Enumerated(EnumType.STRING)
   @Column(name = "user_status")
   private UserStatus userStatus;

   @OneToOne(fetch = FetchType.LAZY)
   private Player player;
}
