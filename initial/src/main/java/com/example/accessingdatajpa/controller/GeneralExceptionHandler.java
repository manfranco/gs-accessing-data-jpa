package com.example.accessingdatajpa.controller;

import com.example.accessingdatajpa.util.exception.DuplicateEntityException;
import com.example.accessingdatajpa.util.exception.EntityNotFoundException;
import com.example.accessingdatajpa.util.exception.ParameterNotFoundException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import javax.validation.ConstraintViolationException;
import javax.validation.ValidationException;

@ControllerAdvice
public class GeneralExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(value = {ConstraintViolationException.class})
    protected ResponseEntity<Object> handleValidationException(ValidationException ex, WebRequest request) {
        return handleExceptionInternal(ex, ex.getMessage(),
                new HttpHeaders(), HttpStatus.CONFLICT, request);
    }

    @ExceptionHandler(value = {EntityNotFoundException.class, ParameterNotFoundException.class})
    protected ResponseEntity<Object> handleNotFoundExceptions(RuntimeException ex, WebRequest request) {
        return handleExceptionInternal(ex, ex.getMessage(),
                new HttpHeaders(), HttpStatus.NOT_FOUND, request);

    }

    @ExceptionHandler(value = {DuplicateEntityException.class})
    protected ResponseEntity<Object> handleDuplicateExceptions(RuntimeException ex, WebRequest request) {
        return handleExceptionInternal(ex, ex.getMessage(),
                new HttpHeaders(), HttpStatus.BAD_REQUEST, request);

    }


    /*
    Given that Request specific exceptions should be handled with the personalized Ex, by default other errors will be defines as an internal server error.
     */
    @ExceptionHandler(value = {Exception.class})
    protected ResponseEntity<Object> handleGeneralExceptions(RuntimeException ex, WebRequest request) {
        return handleExceptionInternal(ex, ex.getMessage(),
                new HttpHeaders(), HttpStatus.INTERNAL_SERVER_ERROR, request);

    }

}