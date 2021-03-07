package sda.exercises.sdaexercises.controllers.security.observable;

public class DefaultGuardEvent implements GuardEvent {
    private final EventType eventType;
    private final String userId;
    private final String requestPath;
    private final String requestMethod;

    private DefaultGuardEvent(Builder builder) {
        this.eventType = builder.eventType;
        this.userId = builder.userId;
        this.requestPath = builder.requestPath;
        this.requestMethod = builder.requestMethod;
    }

    @Override
    public EventType getEventType() {
        return eventType;
    }

    @Override
    public String getUserId() {
        return userId;
    }

    @Override
    public String getRequestPath() {
        return requestPath;
    }

    @Override
    public String getRequestMethod() {
        return requestMethod;
    }

    public static class Builder {
        private EventType eventType;
        private String userId;
        public String requestPath;
        public String requestMethod;

        public Builder(EventType eventType) {
            this.eventType = eventType;
        }

        public DefaultGuardEvent build() {
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

        public Builder withRequestPath(String requestPath) {
            this.requestPath = requestPath;
            return this;
        }

        public Builder withRequestMethod(String requestMethod) {
            this.requestMethod = requestMethod;
            return this;
        }
    }

    // Factory method(s)
    public static DefaultGuardEvent of(EventType eventType, String userId, String requestMethod, String requestPath) {
        return new Builder(eventType)
                .withUserId(userId)
                .withRequestMethod(requestMethod)
                .withRequestPath(requestPath)
                .build();
    }

    public static DefaultGuardEvent of(EventType eventType, String userId) {
        return new Builder(eventType)
                .withUserId(userId)
                .build();
    }
}
