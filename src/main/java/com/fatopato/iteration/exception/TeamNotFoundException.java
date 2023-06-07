package com.fatopato.iteration.exception;

public class TeamNotFoundException extends RuntimeException{
    public TeamNotFoundException(String message) {
        super(message);
    }
    public TeamNotFoundException(Long id) {
        super("Team not found with id: " + id);
    }

}
