package sda.exercises.sdaexercises.exceptions;

public class ExerciseExceptionFactory {
    public static ExerciseException createEntityNotFound(String message) {
        return new EntityNotFoundException(message);
    }
    public static ExerciseException createNoUserHeader(String message) {
        return new NoUserHeaderException(message);
    }
    public static ExerciseException createUserNotFound(String message) {
        return new UserNotFoundException(message);
    }
}
