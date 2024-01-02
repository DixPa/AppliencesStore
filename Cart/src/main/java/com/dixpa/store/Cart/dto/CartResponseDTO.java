package com.dixpa.store.Cart.dto;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class CartResponseDTO {

    private Long id;
    private Long idUser;
    private LocalDateTime createdDate;
    private Double totalPrice;
    private List<ItemCartDTO> items;
}
