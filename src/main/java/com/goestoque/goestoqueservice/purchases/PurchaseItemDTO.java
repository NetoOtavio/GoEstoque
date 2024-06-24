package com.goestoque.goestoqueservice.purchases;

import java.util.UUID;

public record PurchaseItemDTO(
        String inputItemId,
        double purchaseItemValue
) {
}
