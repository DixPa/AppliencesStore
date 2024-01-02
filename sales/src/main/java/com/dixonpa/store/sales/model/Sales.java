package com.dixonpa.store.sales.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class Sales {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Long id_user;
    @Column(name = "sale_number")
    private String saleNumber;
    @Column(name = "price_total")
    private Double priceTotal;
    @Column(name = "created_date")
    private LocalDateTime createdDate;

}
