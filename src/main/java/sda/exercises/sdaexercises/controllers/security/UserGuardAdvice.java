package sda.exercises.sdaexercises.controllers.security;

import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RestController;
import sda.exercises.sdaexercises.controllers.security.observable.DefaultGuardEvent;
import sda.exercises.sdaexercises.controllers.security.observable.GuardEvent.EventType;
import sda.exercises.sdaexercises.controllers.security.observable.UserGuardObservable;
import sda.exercises.sdaexercises.controllers.security.observable.UserGuardObserver;
import sda.exercises.sdaexercises.exceptions.NoUserHeaderException;
import sda.exercises.sdaexercises.exceptions.UserNotFoundException;
import sda.exercises.sdaexercises.services.UserService;

import javax.servlet.http.HttpServletRequest;
import java.util.HashSet;
import java.util.Set;

@Aspect
@Component
public class UserGuardAdvice implements UserGuardObservable {
    private final Set<UserGuardObserver> userGuardObservers = new HashSet<>();

    @Autowired
    private HttpServletRequest request;

    @Autowired
    private UserService userService;

    @Before("@annotation(userGuard) && @target(restController))")
    public void checkUser(RestController restController, UserGuard userGuard){
        final String userIdHeader = request.getHeader("userId");
        if (userIdHeader == null) {
            notifyObservers(EventType.UNDEFINED, null);
            throw new NoUserHeaderException();
        }
        try {
            userService.getUser(Integer.valueOf(userIdHeader));
            notifyObservers(EventType.PASSED, userIdHeader);
        } catch (Exception exception) {
            notifyObservers(EventType.FORBIDDEN, userIdHeader);
            throw new UserNotFoundException("User not found");
        }
    };

    @Override
    public void addObserver(UserGuardObserver userGuardObserver) {
        userGuardObservers.add(userGuardObserver);
    }

    @Override
    public void removeObserver(UserGuardObserver userGuardObserver) {
        userGuardObservers.remove(userGuardObserver);
    }

    private void notifyObservers(EventType eventType, String userIdHeader){
        final DefaultGuardEvent guardEvent = new DefaultGuardEvent.Builder(eventType)
                .withUserId(userIdHeader)
                .build();
        userGuardObservers.forEach(userGuardObserver -> userGuardObserver.update(guardEvent));
    }
}
