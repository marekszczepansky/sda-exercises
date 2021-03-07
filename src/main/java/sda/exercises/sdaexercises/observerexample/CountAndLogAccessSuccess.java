package sda.exercises.sdaexercises.observerexample;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import sda.exercises.sdaexercises.controllers.security.observable.GuardEvent;
import sda.exercises.sdaexercises.controllers.security.observable.UserGuardObservable;
import sda.exercises.sdaexercises.controllers.security.observable.UserGuardObserver;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import java.util.concurrent.atomic.AtomicInteger;

@Service
public class CountAndLogAccessSuccess implements UserGuardObserver {
    private static Logger logger = LoggerFactory.getLogger(CountAndLogAccessSuccess.class);
    private final AtomicInteger counter = new AtomicInteger(0);
    private final UserGuardObservable userGuardObservable;

    public CountAndLogAccessSuccess(UserGuardObservable userGuardObservable) {
        this.userGuardObservable = userGuardObservable;
    }

    @Override
    public void update(GuardEvent guardEvent) {
      if (guardEvent.getEventType() == GuardEvent.EventType.PASSED) {
          final int counter = this.counter.incrementAndGet();
          logger.info("{} successful accesses, last one by: {}", counter, guardEvent.getUserId());
      }
    }

    @PostConstruct
    void postConstruct(){
        userGuardObservable.addObserver(this);
    }

    @PreDestroy
    void preDestroy(){
        userGuardObservable.removeObserver(this);
    }
}
