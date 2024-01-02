package com.dixonpa.store.sales.repository;

import com.dixonpa.store.sales.dto.CartRequestDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "cart", url = "http://localhost:9003/cart")
public interface CartFeignClient {

    @GetMapping("/{id}")
    CartRequestDTO getCartByUserId(@PathVariable Long id);
}
