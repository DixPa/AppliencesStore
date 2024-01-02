package com.dixonpa.store.products.dto;

import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

public record ProductRequestDTO (

        @NotNull(message = "The 'code' cannot be null")
        @Positive(message = "Code dont be negative")
        Long code,
        @NotBlank(message = "The 'name' cannot be empty or null")
        String name,
        @NotBlank(message = "The 'brand' cannot be empty or null")
        String brand,
        @NotNull(message = "The 'price' cannot be null")
        @DecimalMin(value = "0.01", message = "Price must be greater than zero")
        Double price,

        @NotBlank(message = "The 'type' cannot be empty or null")
        String type
) {
}
