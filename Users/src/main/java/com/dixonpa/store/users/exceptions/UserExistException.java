package com.dixonpa.store.users.exceptions;

public class UserExistException extends RuntimeException {

    private final String message;

    public UserExistException() {
        this.message = "User already exist";
    }

    @Override
    public String getMessage() {
        return message;
    }
}
