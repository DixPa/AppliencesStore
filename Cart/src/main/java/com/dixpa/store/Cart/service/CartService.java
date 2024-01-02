package com.dixpa.store.Cart.service;

import com.dixpa.store.Cart.dto.*;
import com.dixpa.store.Cart.model.Cart;
import com.dixpa.store.Cart.model.CartItems;
import com.dixpa.store.Cart.repository.CartItemsRepository;
import com.dixpa.store.Cart.repository.CartRepository;
import com.dixpa.store.Cart.repository.ProductFeignClient;
import com.dixpa.store.Cart.repository.UserFeignClient;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import io.github.resilience4j.retry.annotation.Retry;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Slf4j

public class CartService implements ICartService {

    private final CartRepository repository;
    private final ProductFeignClient productClient;
    private final UserFeignClient userClient;
    private final CartItemsRepository itemsRepository;
    @Override
    public CartResponseDTO decideCreatOrAdd(CartRequestDTO cartRequestDTO) {
        if(repository.findCartByEmail(cartRequestDTO.getEmail()).isPresent()){
            log.info("Add item in: " + cartRequestDTO.getEmail());
            return addItemToCart(cartRequestDTO);
        }else{
            log.info("Cart create in: " + cartRequestDTO.getEmail());
            return saveCart(cartRequestDTO);
        }
    }
    @Override
    @CircuitBreaker(name = "users-service",fallbackMethod = "fallBackGetUser")
    @Retry(name = "users-service")
    public CartResponseDTO saveCart(CartRequestDTO cartRequest) {
        UserDTO user = userClient.getUserByEmail(cartRequest.getEmail());
        Cart cart = Cart.builder()
                .idUser(user.getId())
                .email(user.getEmail())
                .createdDate(LocalDateTime.now())
                .totalPrice(0.0)
                .items(new ArrayList<>())
                .build();
        repository.save(cart);
        addItemToCart(cartRequest);
        return getCart(cart.getIdUser());
    }
    @Override
    public void deleteCart(Long idUser) {
        Cart cart = repository.findCartByIdUser(idUser).orElseThrow();
        repository.deleteById(cart.getId());
    }
    @Override
    public CartResponseDTO addItemToCart(CartRequestDTO cartRequest) {

        ProductDTO product = productClient.getProductById(cartRequest.getIdProduct());
        Cart cart = repository.findCartByEmail(cartRequest.getEmail()).orElseThrow();
        var existProduct = itemsRepository.findItemByIdProductAndCart(cart, cartRequest.getIdProduct());
        if(existProduct.isPresent()){
            return plusQuantityOfProduct(cartRequest);
        }else {
            CartItems cartItems = CartItems.builder()
                    .idProduct(product.getId())
                    .code(product.getCode())
                    .name(product.getName())
                    .brand(product.getBrand())
                    .price(product.getPrice())
                    .quantity(cartRequest.getQuantity())
                    .cart(cart)
                    .build();
            cart.getItems().add(cartItems);
            repository.save(cart);
            updatePriceTotalCart(cart.getEmail());
            return getCart(cart.getIdUser());
        }
    }
    @Override
    public CartResponseDTO getCart (Long idUser){
        Cart cart = repository.findCartByIdUser(idUser).orElseThrow();
        List<ItemCartDTO> itemList = cart.getItems().stream()
                .map(items -> ItemCartDTO.builder()
                        .idProduct(items.getIdProduct())
                        .code(items.getCode())
                        .name(items.getName())
                        .brand(items.getBrand())
                        .price(items.getPrice())
                        .quantity(items.getQuantity())
                        .build()).collect(Collectors.toList());
        return CartResponseDTO.builder()
                .id(cart.getId())
                .idUser(cart.getIdUser())
                .createdDate(cart.getCreatedDate())
                .totalPrice(cart.getTotalPrice())
                .items(itemList)
                .build();
    }
    @Override
    public List<ItemCartDTO> getItemsCart(Cart idCart) {
        var items = itemsRepository.findAllByCart(idCart).orElseThrow();
        return items.stream()
                .map(i ->ItemCartDTO.builder()
                        .idProduct(i.getIdProduct())
                        .code(i.getCode())
                        .name(i.getName())
                        .brand(i.getBrand())
                        .price(i.getPrice())
                        .quantity(i.getQuantity())
                        .build())
                .collect(Collectors.toList());
    }
    @Override
    public void deleteItem(CartRequestDTO cartRequest) {
        Cart cart = repository.findCartByEmail(cartRequest.getEmail()).orElseThrow();
        CartItems cartItem = itemsRepository.findItemByIdProductAndCart(cart, cartRequest.getIdProduct()).orElseThrow();
        itemsRepository.deleteById(cartItem.getId());
        updatePriceTotalCart(cart.getEmail());
    }
    public CartResponseDTO plusQuantityOfProduct(CartRequestDTO cartRequest){
        Cart cart = repository.findCartByEmail(cartRequest.getEmail()).orElseThrow();
        cart.getItems().stream()
                .filter(c->c.getIdProduct().equals(cartRequest.getIdProduct()))
                .findFirst()
                .ifPresent(c ->{
                    c.setQuantity(cartRequest.getQuantity() + c.getQuantity());
                    itemsRepository.save(c);
                    updatePriceTotalCart(cart.getEmail());
                });
        return getCart(cart.getIdUser());
    }
    @Override
    public CartResponseDTO editQuantityOfProduct(CartRequestDTO cartRequest){
        Cart cart =repository.findCartByEmail(cartRequest.getEmail()).orElseThrow();
        cart.getItems().stream()
                .filter(c->c.getIdProduct().equals(cartRequest.getIdProduct()))
                .findFirst()
                .ifPresent(c->{
                    c.setQuantity(cartRequest.getQuantity());
                    itemsRepository.save(c);
                    updatePriceTotalCart(cart.getEmail());
                });
        return getCart(cart.getIdUser());
    }
    public void updatePriceTotalCart(String email){
        Cart updatePriceCart = repository.findCartByEmail(email).orElseThrow();
        double totalPrice = updatePriceCart.getItems().stream()
                        .mapToDouble(cartPrice -> cartPrice.getPrice() * cartPrice.getQuantity())
                        .sum();
        updatePriceCart.setTotalPrice(totalPrice);
        repository.save(updatePriceCart);
    }
    public UserDTO fallBackGetUser(Throwable throwable){
        return UserDTO.builder()
                .id(999999L)
                .name("Fail")
                .lastname("Fail")
                .email("Fail").build();
    }
}
