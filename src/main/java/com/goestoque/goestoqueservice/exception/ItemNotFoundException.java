package com.goestoque.goestoqueservice.exception;

public class ItemNotFoundException extends RuntimeException {

    public ItemNotFoundException(String code) {
        super("Item not found with code: " + code);
    }
}
