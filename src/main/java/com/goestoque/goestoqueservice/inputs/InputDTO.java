package com.goestoque.goestoqueservice.inputs;

import java.util.Date;
import java.util.UUID;

public record InputDTO(
    UUID inputId,
    Date inputDate
) {}
