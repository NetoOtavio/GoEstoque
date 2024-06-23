package com.goestoque.goestoqueservice.exception.dtos;

import java.util.Map;

public record DefaultErrorResponseDTO(
        String exception,
        String message
) {}