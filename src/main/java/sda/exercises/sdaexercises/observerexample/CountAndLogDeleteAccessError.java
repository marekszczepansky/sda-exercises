package sda.exercises.sdaexercises.observerexample;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import sda.exercises.sdaexercises.controllers.security.observable.GuardEvent;
import sda.exercises.sdaexercises.controllers.security.observable.UserGuardObservable;

import java.util.concurrent.atomic.AtomicInteger;

@Service
public class CountAndLogDeleteAccessError extends AbstractUserGuardObserver {
    private static Logger logger = LoggerFactory.getLogger(CountAndLogDeleteAccessError.class);
    private final AtomicInteger counter = new AtomicInteger(0);

    public CountAndLogDeleteAccessError(UserGuardObservable userGuardObservable) {
        super(userGuardObservable);
    }

    @Override
    public void update(GuardEvent guardEvent) {
        if (GuardEvent.EventType.PASSED != guardEvent.getEventType()
                && "DELETE".equalsIgnoreCase(guardEvent.getRequestMethod())) {
            logger.warn(
                    "{} error event(s) for method DELETE, last path:{}, last userId: {}",
                    counter.incrementAndGet(),
                    guardEvent.getRequestPath(),
                    guardEvent.getUserId()
            );
        }
    }
}
