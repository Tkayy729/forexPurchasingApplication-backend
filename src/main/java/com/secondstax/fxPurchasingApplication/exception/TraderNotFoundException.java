package com.secondstax.fxPurchasingApplication.exception;

public class TraderNotFoundException extends RuntimeException{
    public TraderNotFoundException(String error){
        super(error);
    }
}
