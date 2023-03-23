package com.secondstax.fxPurchasingApplication.exception;

public class TraderAlreadyExistException extends RuntimeException {
    public TraderAlreadyExistException(String errorMessage){
        super(errorMessage);
    }
}
