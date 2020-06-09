package com.gruzini.tennistico.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity(name = "activation_tokens")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ActivationToken {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String value;
    @OneToOne
    @JoinColumn(name = "user_id")
    private User user;
}
