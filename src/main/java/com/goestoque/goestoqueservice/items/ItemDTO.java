package com.goestoque.goestoqueservice.items;

public record ItemDTO(
        String code,
        String name,
        int availableQuantity,
        double price,
        String description
) {}