package com.dixpa.store.Cart.model;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
@Table(name = "cart_items")
@Builder
public class CartItems {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "id_product")
    private Long idProduct;
    private Long code;
    private String name;
    private String brand;
    private Double price;
    private Long quantity;

    @ManyToOne
    @JoinColumn(name = "id_cart")
    private Cart cart;

}
