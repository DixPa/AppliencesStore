package com.dixpa.store.Cart.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class CartRequestDTO {

    private String email;
    private Long idProduct;
    private Long quantity;
}
