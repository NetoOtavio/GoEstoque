package com.goestoque.goestoqueservice.inputs;

import java.util.Date;
import java.util.UUID;

public record InputDTO(
    String inputId,
    Date inputDate
) {}
