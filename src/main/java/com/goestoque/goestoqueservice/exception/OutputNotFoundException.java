package com.goestoque.goestoqueservice.exception;

public class OutputNotFoundException extends RuntimeException {
    public OutputNotFoundException(String outputId) {
        super("output not found with id: " + outputId);
    }
}
