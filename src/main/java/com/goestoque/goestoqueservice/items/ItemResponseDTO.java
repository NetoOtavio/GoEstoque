package com.goestoque.goestoqueservice.items;

public record ItemResponseDTO(
        Long id,
        String name,
        int availableQuantity,
        double price,
        String description
) {}