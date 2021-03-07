package sda.exercises.sdaexercises.observerexample;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import sda.exercises.sdaexercises.controllers.security.observable.GuardEvent;
import sda.exercises.sdaexercises.controllers.security.observable.UserGuardObservable;

import java.util.concurrent.atomic.AtomicInteger;

@Service
public class CountAndLogAccessSuccess extends AbstractUserGuardObserver {
    private static Logger logger = LoggerFactory.getLogger(CountAndLogAccessSuccess.class);
    private final AtomicInteger counter = new AtomicInteger(0);

    public CountAndLogAccessSuccess(UserGuardObservable userGuardObservable) {
        super(userGuardObservable);
    }

    @Override
    public void update(GuardEvent guardEvent) {
      if (guardEvent.getEventType() == GuardEvent.EventType.PASSED) {
          final int counter = this.counter.incrementAndGet();
          logger.info(
                  "{} successful accesses, last one by: {} to {}",
                  counter,
                  guardEvent.getUserId(),
                  guardEvent.getRequestPath()
          );
      }
    }
}
