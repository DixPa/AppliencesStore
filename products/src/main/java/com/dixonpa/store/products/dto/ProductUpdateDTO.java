package com.dixonpa.store.products.dto;

import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.Positive;

public record ProductUpdateDTO(

        @Positive(message = "Code dont be negative")
        Long code,
        String name,
        String brand,
        @DecimalMin(value = "0.01", message = "Price must be greater than zero")
        Double price,
        String type
) {
}



