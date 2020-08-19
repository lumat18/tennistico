package com.gruzini.tennistico.models.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class NotificationDto {
    private Long id;
    private String message;
    private String positiveEndpoint;
    private String negativeEndpoint;
    private String buttonName;
    private Boolean clicked;
    private LocalDateTime createdAt;
    private Long matchId;
}
