package com.goestoque.goestoqueservice.exception;

public class ItemAlreadyExistsException extends RuntimeException {
    public ItemAlreadyExistsException(String code) {
        super("item already exists with code: " + code);
    }
}
