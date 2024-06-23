package com.goestoque.goestoqueservice.exception;

public class ItemNotFoundException extends RuntimeException {

    public ItemNotFoundException(String codigo) {
        super("Item not found with code: " + codigo);
    }
}
