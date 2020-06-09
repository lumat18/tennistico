package com.gruzini.tennistico.emails;

public enum MessageType {
    ACTIVATION {
        @Override
        public String getComponentName() {
            return "activation";
        }

        @Override
        public Class<ActivationMessageCreator> getComponentClass() {
            return ActivationMessageCreator.class;
        }
    },
    RESET {
        @Override
        public String getComponentName() {
            return "reset";
        }

        @Override
        public Class<? extends MessageCreator> getComponentClass() {
            return null;
        }
    };

    public abstract String getComponentName();

    public abstract Class<? extends MessageCreator> getComponentClass();
}
