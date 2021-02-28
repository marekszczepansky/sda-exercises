package sda.exercises.sdaexercises.controllers;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import sda.exercises.sdaexercises.exceptions.EntityNotFoundException;
import sda.exercises.sdaexercises.exceptions.NoUserHeaderException;
import sda.exercises.sdaexercises.exceptions.UserNotFoundException;

@ControllerAdvice
public class TwitterExceptionHandler {

    @ExceptionHandler(EntityNotFoundException.class)
    @ResponseStatus(value = HttpStatus.NOT_FOUND, reason = "Entity not found")
    public void entityNotFoundHandler(){ }

    @ExceptionHandler(UserNotFoundException.class)
    @ResponseStatus(value = HttpStatus.FORBIDDEN, reason = "User not found")
    public void userNotFoundHandler(){ }

    @ExceptionHandler(NoUserHeaderException.class)
    @ResponseStatus(value = HttpStatus.UNAUTHORIZED, reason = "User userId header not found")
    public void noUserHeaderHandler(){ }
}
