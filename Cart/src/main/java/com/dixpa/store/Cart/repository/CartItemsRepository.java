package com.dixpa.store.Cart.repository;

import com.dixpa.store.Cart.model.Cart;
import com.dixpa.store.Cart.model.CartItems;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import java.util.List;
import java.util.Optional;

@Repository
public interface CartItemsRepository extends JpaRepository<CartItems, Long> {

    @Query("SELECT p FROM CartItems p WHERE p.cart = :idCart")
    Optional<List<CartItems>> findAllByCart(Cart idCart);
    @Query("SELECT c FROM CartItems c WHERE c.cart = :cart AND c.idProduct = :id")
    Optional<CartItems> findItemByIdProductAndCart(Cart cart, Long id);
}
