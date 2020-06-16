package com.gruzini.tennistico.domain;

import com.gruzini.tennistico.domain.enums.NotificationType;
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

    @Column(name = "match_id")
    private Long matchId;

    @Enumerated(EnumType.STRING)
    @Column(name = "type")
    private NotificationType notificationType;

    @Column(name = "created_at")
    private LocalDateTime createdAt;
}
