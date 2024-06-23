package com.goestoque.goestoqueservice.exception.dtos;

import java.util.Map;

public record MapErrorResponseDTO(
    String exception,
    Map errors
) {}