package com.dixpa.store.Cart.repository;

import com.dixpa.store.Cart.dto.ProductDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "product", url = "http://localhost:9001/product")
public interface ProductFeignClient {

    @GetMapping("/{id}")
    ProductDTO getProductById(@PathVariable Long id);
}
