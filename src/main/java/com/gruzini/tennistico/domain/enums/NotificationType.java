package com.gruzini.tennistico.domain.enums;

import com.gruzini.tennistico.messages.NotificationMessages;

public enum NotificationType {

    MATCH_CREATED{
        @Override
        public String getEndpoint() {
            return null;
        }

        @Override
        public String getMessage() {
            return NotificationMessages.MATCH_CREATED;
        }
    },
    JOIN_REQUEST{
        @Override
        public String getEndpoint() {
            return "/confirm-join";
        }
        @Override
        public String getMessage() {
            return NotificationMessages.JOIN_REQUEST;
        }
    },
    JOIN_CONFIRMED{
        @Override
        public String getEndpoint() {
            return null;
        }
        @Override
        public String getMessage() {
            return NotificationMessages.JOIN_CONFIRMED;
        }
    },

    SCORE_TO_SUBMIT{
        @Override
        public String getEndpoint() {
            return "/submit-score";
        }
        @Override
        public String getMessage() {
            return NotificationMessages.SCORE_TO_SUBMIT;
        }
    },
    SCORE_TO_CONFIRM{
        @Override
        public String getEndpoint() {
            return "/confirm-score";
        }
        @Override
        public String getMessage() {
            return NotificationMessages.SCORE_TO_CONFIRM;
        }
    };

    public abstract String getEndpoint();
    public abstract String getMessage();
}
