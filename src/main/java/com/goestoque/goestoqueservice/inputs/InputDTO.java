package com.goestoque.goestoqueservice.inputs;

import java.util.UUID;

public record InputDTO(
    UUID inputId,
    String message
) {}
