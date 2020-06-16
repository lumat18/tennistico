package com.gruzini.tennistico.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.List;

@Entity(name = "notifications")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Notification {

    @Id
    @GeneratedValue
    private Long id;

    @OneToMany
    @JoinColumn(name = "recipents")
    @NotNull
    private List<User> recipients;
    @Column(name = "action_link")
    private String actionLink;
    @Column(name = "target_id")
    private Long targetId;
    private String message;
}
