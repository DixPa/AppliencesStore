package com.dixpa.store.Cart.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class ItemCartDTO {

    private Long idProduct;
    private Long code;
    private String name;
    private String brand;
    private Double price;
    private Long quantity;

}
