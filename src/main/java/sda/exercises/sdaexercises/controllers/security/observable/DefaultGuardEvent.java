package sda.exercises.sdaexercises.controllers.security.observable;

public class DefaultGuardEvent implements GuardEvent {
    private final EventType eventType;
    private final String userId;

    private DefaultGuardEvent(Builder builder) {
        this.eventType = builder.eventType;
        this.userId = builder.userId;
    }

    @Override
    public EventType getEventType() {
        return eventType;
    }

    @Override
    public String getUserId() {
        return userId;
    }

    public static class Builder{
        private EventType eventType;
        private String userId;

        public Builder(EventType eventType) {
            this.eventType = eventType;
        }

        public DefaultGuardEvent build(){
            return new DefaultGuardEvent(this);
        }

        /**
         * redundant method since we have this value from constructor
         */
        public Builder withEventType(EventType eventType) {
            this.eventType = eventType;
            return this;
        }

        public Builder withUserId(String userId) {
            this.userId = userId;
            return this;
        }
    }
}
