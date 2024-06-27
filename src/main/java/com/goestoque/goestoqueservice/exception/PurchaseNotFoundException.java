package com.goestoque.goestoqueservice.exception;

public class PurchaseNotFoundException extends RuntimeException {
    public PurchaseNotFoundException(String id) {
        super("Purchase not found with id: " + id);
    }
}
