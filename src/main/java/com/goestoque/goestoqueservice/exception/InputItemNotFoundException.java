package com.goestoque.goestoqueservice.exception;

public class InputItemNotFoundException extends RuntimeException {
    public InputItemNotFoundException(String inputItemId) {
        super("input item not found with id: " + inputItemId);
    }
}
