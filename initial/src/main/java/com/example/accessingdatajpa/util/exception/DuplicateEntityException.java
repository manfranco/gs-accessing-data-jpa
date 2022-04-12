package com.example.accessingdatajpa.util.exception;

public class DuplicateEntityException  extends Exception{
    public DuplicateEntityException(String errorMessage) {
        super(errorMessage);
    }
}
