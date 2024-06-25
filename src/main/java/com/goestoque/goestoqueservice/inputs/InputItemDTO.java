package com.goestoque.goestoqueservice.inputs;

import com.goestoque.goestoqueservice.items.ItemDTO;

public record InputItemDTO(
        String code,
        int amount
) {}