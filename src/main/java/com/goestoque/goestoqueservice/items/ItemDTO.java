package com.goestoque.goestoqueservice.items;

public record ItemDTO(
        String code,
        String name,
        double price,
        String description
) {}