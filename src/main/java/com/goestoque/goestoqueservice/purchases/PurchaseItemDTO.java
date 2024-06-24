package com.goestoque.goestoqueservice.purchases;

import java.util.UUID;

public record PurchaseItemDTO(
        UUID inputItemId,
        double purchaseItemValue
) {
}
