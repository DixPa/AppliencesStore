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
public class SalesResponseDTO {
    private Long id_user;
    private String saleNumber;
    private Double priceTotal;
    private LocalDateTime createdDate;
}
