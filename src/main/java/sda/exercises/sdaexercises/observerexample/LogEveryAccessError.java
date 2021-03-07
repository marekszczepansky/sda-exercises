package sda.exercises.sdaexercises.observerexample;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import sda.exercises.sdaexercises.controllers.security.observable.GuardEvent;
import sda.exercises.sdaexercises.controllers.security.observable.UserGuardObservable;
import sda.exercises.sdaexercises.controllers.security.observable.UserGuardObserver;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

@Service
public class LogEveryAccessError implements UserGuardObserver {
    private Logger logger = LoggerFactory.getLogger(LogEveryAccessError.class);
    private final UserGuardObservable userGuardObservable;

    public LogEveryAccessError(UserGuardObservable userGuardObservable) {
        this.userGuardObservable = userGuardObservable;
    }

    @Override
    public void update(GuardEvent guardEvent) {
        final GuardEvent.EventType eventType = guardEvent.getEventType();
        if (eventType != GuardEvent.EventType.PASSED) {
            logger.warn("Guard error event observed {}, userId: {}", eventType, guardEvent.getUserId());
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
