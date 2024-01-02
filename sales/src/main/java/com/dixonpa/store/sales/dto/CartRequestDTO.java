package com.dixonpa.store.sales.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class CartRequestDTO {
    private Long id_user;
    private LocalDateTime createdDate;
    private Double totalPrice;
}
