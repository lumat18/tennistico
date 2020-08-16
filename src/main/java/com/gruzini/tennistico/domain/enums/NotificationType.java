package com.gruzini.tennistico.domain.enums;

import com.gruzini.tennistico.messages.NotificationMessages;
import lombok.Getter;

@Getter
public enum NotificationType {

    MATCH_CREATED(null, null, NotificationMessages.MATCH_CREATED, null),
    JOIN_REQUEST("/confirm-join", "/reject-join", NotificationMessages.JOIN_REQUEST, "CONFIRM"),
    JOIN_REQUEST_REJECTED(null, null, NotificationMessages.JOIN_REQUEST_REJECTED, null),
    JOIN_CONFIRMED(null, null, NotificationMessages.JOIN_CONFIRMED, null),
    SCORE_TO_SUBMIT("/input-score/begin", null, NotificationMessages.SCORE_TO_SUBMIT, "FILL IN"),
    SCORE_TO_CONFIRM("/confirm-score", "/reject-score", NotificationMessages.SCORE_TO_CONFIRM, "CONFIRM"),
    SCORE_REJECTED("/input-score/begin", null, NotificationMessages.SCORE_REJECTED, "CONFIRM"),
    MATCH_ARCHIVED(null, null, NotificationMessages.MATCH_ARCHIVED, null);

    private final String positiveEndpoint;
    private final String negativeEndpoint;
    private final String message;
    private final String buttonName;

    NotificationType(final String positiveEndpoint, String negativeEndpoint, final String message, final String buttonName) {
        this.positiveEndpoint = positiveEndpoint;
        this.negativeEndpoint = negativeEndpoint;
        this.message = message;
        this.buttonName = buttonName;
    }
}
