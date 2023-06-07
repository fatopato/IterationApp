package com.fatopato.iteration.exception;

public class BaseEntityNotFoundException extends RuntimeException{
    public BaseEntityNotFoundException(String message) {
        super(message);
    }

    public BaseEntityNotFoundException(Long id) {
        super("Entity not found with id: " + id);
    }

    public BaseEntityNotFoundException(String entityName, Long id) {
        super(entityName + " not found with id: " + id);
    }

}