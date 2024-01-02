package com.dixonpa.store.products.dto;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class ProductResponseDTO {
    private Long id;
    private Long code;
    private String name;
    private String brand;
    private Double price;
    private String type;
}
