package com.goestoque.goestoqueservice.purchases;

public record PurchaseItemDTO(
        String itemCode,
        int amount,
        double purchaseItemPrice
) {
}
