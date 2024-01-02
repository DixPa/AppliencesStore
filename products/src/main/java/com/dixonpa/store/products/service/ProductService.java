package com.dixonpa.store.products.service;

import com.dixonpa.store.products.dto.ProductRequestDTO;
import com.dixonpa.store.products.dto.ProductResponseDTO;
import com.dixonpa.store.products.dto.ProductUpdateDTO;
import com.dixonpa.store.products.exceptions.ProductNotFoundException;
import com.dixonpa.store.products.exceptions.SameProductException;
import com.dixonpa.store.products.model.Product;
import com.dixonpa.store.products.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ProductService implements IProductService {

    private final ProductRepository repository;
    @Override
    public ProductResponseDTO saveProduct(ProductRequestDTO productDTO) {
        var repeatProduct = repository.findProductByCode(productDTO.code());
        if(repeatProduct.isPresent()){
            throw new SameProductException(productDTO.code());
        }else {
            var product = Product.builder()
                    .code(productDTO.code())
                    .name(productDTO.name())
                    .brand(productDTO.brand())
                    .price(productDTO.price())
                    .type(productDTO.type())
                    .build();
            repository.save(product);
            return getProductById(product.getId());
        }
    }
    @Override
    public void deleteAllProducts() {
        repository.deleteAll();
    }
    @Override
    public void deleteProductById(Long id) {
        if (repository.findById(id).isEmpty()){ throw new ProductNotFoundException("Product Not found by DixPa");
        }
        else repository.deleteById(id);
    }
    @Override
    public ProductResponseDTO updateProductById(Long id, ProductUpdateDTO productDTO) {
        Product updateProduct = repository.findById(id).orElseThrow();
        if (productDTO.code() != null) updateProduct.setCode(productDTO.code());
        if (productDTO.name() !=null) updateProduct.setName(productDTO.name());
        if (productDTO.brand() !=null) updateProduct.setBrand(productDTO.brand());
        if (productDTO.price() !=null) updateProduct.setPrice(productDTO.price());
        if (productDTO.type() !=null) updateProduct.setType(productDTO.type());

        repository.save(updateProduct);
        return getProductById(updateProduct.getId());
    }
    @Override
    public ProductResponseDTO getProductById(Long id) {
        if (repository.findById(id).isEmpty()) {
            throw new ProductNotFoundException();
        }else {
            Product getProduct = repository.findById(id).orElseThrow();
            return ProductResponseDTO.builder()
                    .id(getProduct.getId())
                    .code(getProduct.getCode())
                    .name(getProduct.getName())
                    .brand(getProduct.getBrand())
                    .price(getProduct.getPrice())
                    .type(getProduct.getType())
                    .build();
        }
    }
    @Override
    public List<ProductResponseDTO> getAllProducts() {
        List<Product> productList = repository.findAll();
        return productList.stream().map(product -> ProductResponseDTO.builder()
                .id(product.getId())
                .code(product.getCode())
                .name(product.getName())
                .brand(product.getBrand())
                .price(product.getPrice())
                .type(product.getType()).build()).collect(Collectors.toList());
    }
}
