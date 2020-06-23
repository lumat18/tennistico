package com.gruzini.tennistico.domain.enums;

import com.gruzini.tennistico.messages.NotificationMessages;
import lombok.Getter;

@Getter
public enum NotificationType {

    MATCH_CREATED(null, NotificationMessages.MATCH_CREATED, null),
    JOIN_REQUEST("/confirm-join", NotificationMessages.JOIN_REQUEST, "CONFIRM"),
    JOIN_CONFIRMED(null, NotificationMessages.JOIN_CONFIRMED, null),
    SCORE_TO_SUBMIT("/input-score/begin", NotificationMessages.SCORE_TO_SUBMIT, "FILL IN"),
    SCORE_TO_CONFIRM("/confirm-score", NotificationMessages.SCORE_TO_CONFIRM, "CONFIRM"),
    MATCH_ARCHIVED(null, NotificationMessages.MATCH_ARCHIVED, null);

    private final String endpoint;
    private final String message;
    private final String buttonName;

    NotificationType(final String endpoint, final String message, final String buttonName) {
        this.endpoint = endpoint;
        this.message = message;
        this.buttonName = buttonName;
    }
}
