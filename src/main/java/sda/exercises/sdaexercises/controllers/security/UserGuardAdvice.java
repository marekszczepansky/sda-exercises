package sda.exercises.sdaexercises.controllers.security;

import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RestController;
import sda.exercises.sdaexercises.exceptions.EntityNotFoundException;
import sda.exercises.sdaexercises.exceptions.NoUserHeaderException;
import sda.exercises.sdaexercises.exceptions.UserNotFoundException;
import sda.exercises.sdaexercises.services.UserService;

import javax.servlet.http.HttpServletRequest;

@Aspect
@Component
public class UserGuardAdvice {

    @Autowired
    private HttpServletRequest request;

    @Autowired
    private UserService userService;

    @Before("@annotation(userGuard) && @target(restController))")
    public void checkUser(RestController restController, UserGuard userGuard){
        final String userIdHeader = request.getHeader("userId");
        if (userIdHeader == null) {
            throw new NoUserHeaderException();
        }
        try {
            userService.getUser(Integer.valueOf(userIdHeader));
        } catch (Exception exception) {
            throw new UserNotFoundException("User not found");
        }
    };
}
