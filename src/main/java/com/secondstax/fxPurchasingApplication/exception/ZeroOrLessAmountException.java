package com.secondstax.fxPurchasingApplication.exception;

public class ZeroOrLessAmountException extends RuntimeException{
    public ZeroOrLessAmountException(String error){
        super(error);
    }
}
