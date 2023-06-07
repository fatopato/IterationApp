package com.fatopato.iteration.exception;

public class TeamNotFoundException extends BaseEntityNotFoundException{
    public TeamNotFoundException(Long id) {
        super("Team", id);
    }

}
