package sda.exercises.sdaexercises.controllers.security.observable;

public interface GuardEvent {
    enum EventType {
        UNDEFINED, FORBIDDEN, PASSED
    }

    EventType getEventType();

    String getUserId();

    String getRequestPath();

    String getRequestMethod();
}
