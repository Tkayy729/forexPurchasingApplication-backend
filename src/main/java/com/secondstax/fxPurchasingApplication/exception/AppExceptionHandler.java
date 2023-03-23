package com.secondstax.fxPurchasingApplication.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

@ControllerAdvice
public class AppExceptionHandler {

    @ExceptionHandler(TraderAlreadyExistException.class)
    public ResponseEntity<String> handleDuplicateTraders(TraderAlreadyExistException exception) {
        return new ResponseEntity<>(exception.getMessage(), HttpStatus.CONFLICT);
    }
    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<String> handleEmptyResources(ResourceNotFoundException exception) {
        return new ResponseEntity<>(exception.getMessage(), HttpStatus.NOT_FOUND);
    }

}