package sda.exercises.sdaexercises.controllers.security.observable;


/**
 * Observer -> Subscriber - one who is watching
 * Observable -> Publisher - one who is watched
 */
public interface UserGuardObservable {
    void addObserver(UserGuardObserver userGuardObserver);
    void removeObserver(UserGuardObserver userGuardObserver);
}
