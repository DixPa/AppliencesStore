package com.dixonpa.store.products.service;

import com.dixonpa.store.products.dto.ProductRequestDTO;
import com.dixonpa.store.products.dto.ProductResponseDTO;
import com.dixonpa.store.products.dto.ProductUpdateDTO;

import java.util.List;

public interface IProductService {

    ProductResponseDTO saveProduct(ProductRequestDTO productDTO);
    void deleteAllProducts();
    void deleteProductById(Long id);
    ProductResponseDTO updateProductById(Long id, ProductUpdateDTO productDTO);
    ProductResponseDTO getProductById(Long id);
    List<ProductResponseDTO> getAllProducts();
}
