package com.gruzini.tennistico.emails;

import lombok.Getter;

@Getter
public enum MessageType {
    ACTIVATION("activation", ActivationMessageCreator.class),
    RESET("reset", null);

    private final String componentName;
    private final Class<? extends MessageCreator> componentClass;

    MessageType(final String componentName, final Class<? extends MessageCreator> componentClass) {
        this.componentName = componentName;
        this.componentClass = componentClass;
    }
}
