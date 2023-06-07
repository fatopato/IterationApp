package com.fatopato.iteration.exception;

public class BaseBadRequestException extends RuntimeException{
    public BaseBadRequestException(String message) {
        super(message);
    }
}
