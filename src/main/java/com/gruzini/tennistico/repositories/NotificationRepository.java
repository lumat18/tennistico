package com.gruzini.tennistico.repositories;

import com.gruzini.tennistico.domain.Notification;
import com.gruzini.tennistico.domain.enums.NotificationType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDateTime;
import java.util.List;

public interface NotificationRepository extends JpaRepository<Notification, Long> {
    @Query("SELECT n FROM  notifications n WHERE n.recipient.email = :username ORDER BY n.createdAt DESC")
    List<Notification> findAllByRecipient(@Param("username") final String username);

    List<Notification> findAllByMatchId(final Long matchId);

    boolean existsByMatchIdAndNotificationTypeAndCreatedAtAfter(Long matchId, NotificationType notificationType ,LocalDateTime date);

    Notification getTopByMatchIdAndAndNotificationTypeOrderByCreatedAtDesc(Long matchId, NotificationType notificationType);
}
