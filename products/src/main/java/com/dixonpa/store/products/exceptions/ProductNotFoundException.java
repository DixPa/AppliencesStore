package com.dixonpa.store.products.exceptions;

public class
ProductNotFoundException extends RuntimeException {
    private final String message;

    public ProductNotFoundException(){
        this.message = "Product Not Found";
    }
    public ProductNotFoundException(String customMessage){
        this.message = customMessage;
    }

    @Override
    public String getMessage() {
        return message ;
    }
}
