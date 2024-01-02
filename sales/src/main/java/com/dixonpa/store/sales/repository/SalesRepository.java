package com.dixonpa.store.sales.repository;

import com.dixonpa.store.sales.model.Sales;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface SalesRepository extends JpaRepository<Sales, Long> {

    @Query("SELECT p FROM Sales p WHERE p.id_user =:id_user")
    Optional<Sales> findById_user(Long id_user);
}
