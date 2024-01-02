package com.dixpa.store.Cart.repository;

import com.dixpa.store.Cart.model.Cart;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import java.util.Optional;

@Repository
public interface CartRepository extends JpaRepository<Cart,Long> {
    @Query("SELECT p FROM Cart p WHERE p.email = :email")
    Optional<Cart> findCartByEmail(String email);

    @Query("SELECT p FROM Cart p WHERE p.idUser = :id")
    Optional<Cart> findCartByIdUser(Long id);
}
