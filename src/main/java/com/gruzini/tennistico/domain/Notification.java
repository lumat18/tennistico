package com.gruzini.tennistico.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;

@Entity(name = "notifications")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Notification {

    @Id
    @GeneratedValue
    private Long id;

    @ManyToOne
    @NotNull
    private User recipient;
    @Column(name = "action_link")
    private String actionLink;
    @Column(name = "target_id")
    private Long targetId;
    private String message;
    private LocalDateTime localDateTime;
}