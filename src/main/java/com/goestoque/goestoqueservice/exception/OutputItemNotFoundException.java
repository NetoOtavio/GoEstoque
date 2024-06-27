package com.goestoque.goestoqueservice.exception;

public class OutputItemNotFoundException extends RuntimeException {

    public OutputItemNotFoundException(String outputItemId) {
        super("output item not found with id: " + outputItemId);
    }
}
