package com.example.accessingdatajpa.util.exception;

public class EntityNotFoundException extends Exception{
    public EntityNotFoundException(String errorMessage) {
        super(errorMessage);
    }
}
