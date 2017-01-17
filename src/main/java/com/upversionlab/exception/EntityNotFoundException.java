package com.upversionlab.exception;

/**
 * Created by rborcat on 1/12/2017.
 */
public class EntityNotFoundException extends RuntimeException{
    public EntityNotFoundException(String message) {
        super(message);
    }
}
