package com.goestoque.goestoqueservice.exception;

public class UserNotFoundException extends RuntimeException {
    public UserNotFoundException(String email) { super("User not found whith e-mail; " + email); }
}
