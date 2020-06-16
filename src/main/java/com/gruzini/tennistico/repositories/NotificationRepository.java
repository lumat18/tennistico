package com.gruzini.tennistico.repositories;

import com.gruzini.tennistico.domain.Notification;
import org.springframework.data.jpa.repository.JpaRepository;

public interface NotificationRepository extends JpaRepository<Notification, Long> {
}
