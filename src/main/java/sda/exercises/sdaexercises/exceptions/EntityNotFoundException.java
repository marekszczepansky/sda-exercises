package sda.exercises.sdaexercises.exceptions;

public class EntityNotFoundException extends RuntimeException {
    private final EntityType entityType;

    public enum EntityType {
        BUSINESS, SECURITY
    }
    public EntityNotFoundException(String message, EntityType entityType) {
        super(message);
        this.entityType = entityType;
    }

    public EntityType getEntityType() {
        return entityType;
    }
}
