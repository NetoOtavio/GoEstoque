package com.goestoque.goestoqueservice.items;

public record CreateRequestDTO(
        String code,
        String name,
        int availableQuantity,
        double price,
        String description
) {}
