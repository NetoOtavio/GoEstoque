package com.goestoque.goestoqueservice.inputs;

import com.goestoque.goestoqueservice.items.ItemDTO;

public record InputItemDTO(
        int amount,
        String code
) {}