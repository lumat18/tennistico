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

        @Override
        public String getButtonName() {
            return null;
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

        @Override
        public String getButtonName() {
            return "CONFIRM";
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

        @Override
        public String getButtonName() {
            return null;
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

        @Override
        public String getButtonName() {
            return "FILL IN";
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

        @Override
        public String getButtonName() {
            return "CONFIRM";
        }
    },
    MATCH_ARCHIVED {
        @Override
        public String getEndpoint() {
            return null;
        }

        @Override
        public String getMessage() {
            return NotificationMessages.MATCH_ARCHIVED;
        }

        @Override
        public String getButtonName() {
            return null;
        }
    };

    public abstract String getEndpoint();

    public abstract String getMessage();

    public abstract String getButtonName();
}
