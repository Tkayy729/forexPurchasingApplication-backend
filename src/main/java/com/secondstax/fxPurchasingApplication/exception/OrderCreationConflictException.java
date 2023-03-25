package com.secondstax.fxPurchasingApplication.exception;

public class OrderCreationConflictException extends RuntimeException{
   public OrderCreationConflictException (String error){
       super(error);
   }
}
