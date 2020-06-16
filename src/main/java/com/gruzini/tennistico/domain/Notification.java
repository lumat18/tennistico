package com.gruzini.tennistico.domain;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity(name = "notifications")
public class Notification {
    @Id
    @GeneratedValue
    private Long id;
    private String message;
}
