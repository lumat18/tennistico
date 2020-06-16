package com.gruzini.tennistico.domain;

import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity(name = "notifications")
@NoArgsConstructor
@Builder
@Data
public class Notification {
    @Id
    @GeneratedValue
    private Long id;
    private String message;
}
