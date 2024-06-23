package com.goestoque.goestoqueservice.auth;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

public record RegisterRequestDTO(
        @NotBlank(message = "Name is mandatory")
        @Size(min = 3, message = "Name must be at least 3 characters long")
        @Pattern(regexp = "^[A-Za-z ]+$", message = "Name must contain only letters and spaces")
        String name,

        @Email(message = "Email should be valid")
        @NotBlank(message = "Email is mandatory")
        String email,

        @NotBlank(message = "Password is mandatory")
        @Pattern(
                regexp = "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)(?=.*[@$!%*?&])[A-Za-z\\d@$!%*?&]{8,}$",
                message = "Password must contain at least one uppercase letter, one lowercase letter, one number,"
                        + "one special character, and must be at least 8 characters long")
        String password
) {}
