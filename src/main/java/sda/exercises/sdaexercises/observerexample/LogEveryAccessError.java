package sda.exercises.sdaexercises.observerexample;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import sda.exercises.sdaexercises.controllers.security.observable.GuardEvent;
import sda.exercises.sdaexercises.controllers.security.observable.UserGuardObservable;

@Service
public class LogEveryAccessError extends AbstractUserGuardObserver {
    private Logger logger = LoggerFactory.getLogger(LogEveryAccessError.class);

    public LogEveryAccessError(UserGuardObservable userGuardObservable) {
        super(userGuardObservable);
    }

    @Override
    public void update(GuardEvent guardEvent) {
        final GuardEvent.EventType eventType = guardEvent.getEventType();
        if (eventType != GuardEvent.EventType.PASSED) {
            logger.warn(
                    "Guard error event observed {}, userId: {}, method: {}, path: {}",
                    eventType,
                    guardEvent.getUserId(),
                    guardEvent.getRequestMethod(),
                    guardEvent.getRequestPath()
            );
        }
    }
}
