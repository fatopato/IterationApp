package com.fatopato.iteration.exception;

public class OrganizationNotFoundException extends RuntimeException{
    public OrganizationNotFoundException(String message) {
        super(message);
    }
    public OrganizationNotFoundException(Long id) {
        super("Organization not found with id: " + id);
    }
}