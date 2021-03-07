package sda.exercises.sdaexercises.observerexample;

import sda.exercises.sdaexercises.controllers.security.observable.UserGuardObservable;
import sda.exercises.sdaexercises.controllers.security.observable.UserGuardObserver;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

public abstract class AbstractUserGuardObserver implements UserGuardObserver {
    private final UserGuardObservable userGuardObservable;

    public AbstractUserGuardObserver(UserGuardObservable userGuardObservable) {
        this.userGuardObservable = userGuardObservable;
    }

    @PostConstruct
    void postConstruct() {
        userGuardObservable.addObserver(this);
    }

    @PreDestroy
    void preDestroy() {
        userGuardObservable.removeObserver(this);
    }
}
