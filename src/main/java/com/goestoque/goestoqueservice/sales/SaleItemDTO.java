package com.goestoque.goestoqueservice.sales;

public record SaleItemDTO(
        String itemCode,
        int amount,
        double saleItemPrice
) {}
