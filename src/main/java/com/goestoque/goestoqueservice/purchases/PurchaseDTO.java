package com.goestoque.goestoqueservice.purchases;

import java.util.UUID;

public record PurchaseDTO (
        String inputId,
        double purchaseValue
){
}
