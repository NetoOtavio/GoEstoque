package com.goestoque.goestoqueservice.purchases;

import java.util.UUID;

public record PurchaseDTO (
        UUID inputId,
        double purchaseValue
){
}
