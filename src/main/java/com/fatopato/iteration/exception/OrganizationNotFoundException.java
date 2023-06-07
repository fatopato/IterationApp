package com.fatopato.iteration.exception;

public class OrganizationNotFoundException extends BaseEntityNotFoundException{

    public OrganizationNotFoundException(Long id) {
        super("Organization", id);
    }
}