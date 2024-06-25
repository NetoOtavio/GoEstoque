package com.goestoque.goestoqueservice.exception;

public class InputNotFoundException extends RuntimeException {

    public InputNotFoundException(String inputId) { super("Input not found with id: " + inputId ); }
}
