package com.dixpa.store.Cart.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.ArrayList;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
@Builder
@Table(name = "cart")
public class Cart {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "id_user")
    private Long idUser;
    @Email
    private String email;
    @Column(name = "created_date")
    private LocalDateTime createdDate;
    @Column(name = "price")
    private Double totalPrice;
    @OneToMany(mappedBy = "cart", cascade = CascadeType.ALL )
    private List<CartItems> items = new ArrayList<>();

}
