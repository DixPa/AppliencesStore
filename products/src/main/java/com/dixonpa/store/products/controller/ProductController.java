package com.dixonpa.store.products.controller;

import com.dixonpa.store.products.dto.ApiResponse;
import com.dixonpa.store.products.dto.ProductRequestDTO;
import com.dixonpa.store.products.dto.ProductResponseDTO;
import com.dixonpa.store.products.dto.ProductUpdateDTO;
import com.dixonpa.store.products.service.IProductService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/product")
public class ProductController {

    private final IProductService service;

    @PostMapping("/save")
    public ResponseEntity<ProductResponseDTO> saveProduct(@RequestBody @Valid ProductRequestDTO productDTO){
        return new ResponseEntity<>(service.saveProduct(productDTO),HttpStatus.CREATED);
    }
    @PutMapping("/update/{id}")
    public ProductResponseDTO updateProduct (@PathVariable Long id, @RequestBody @Valid ProductUpdateDTO productDTO){
        return service.updateProductById(id, productDTO);
    }
    @GetMapping("/{id}")
    public ProductResponseDTO getProductById(@PathVariable Long id){
        return service.getProductById(id);
    }
    @GetMapping
    public List<ProductResponseDTO> getAllProducts (){
        return service.getAllProducts();
    }
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<ApiResponse> deleteProductById(@PathVariable Long id){
        service.deleteProductById(id);
        return new ResponseEntity<>(new ApiResponse("delete"), HttpStatus.OK);
    }
    @DeleteMapping
    public ResponseEntity<String> deleteAllProducts(){
        service.deleteAllProducts();
        return ResponseEntity.ok().body("Delete successful");
    }
}
