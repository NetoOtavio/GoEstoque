package com.goestoque.goestoqueservice.exception;

public class SaleNotFoundException extends RuntimeException {
    public SaleNotFoundException(String saleId) {
        super("sale not found with id: " + saleId);
    }
}
