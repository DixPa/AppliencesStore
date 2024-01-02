package com.dixonpa.store.products.exceptions;

public class SameProductException extends RuntimeException{
    private final String message;

    public SameProductException(Long code){
        this.message = "Same existing product-code: " + code;
    }
    public SameProductException(String customMessage, Long code){
        this.message = customMessage + code;
    }

    @Override
    public String getMessage() {
        return message;
    }
}
