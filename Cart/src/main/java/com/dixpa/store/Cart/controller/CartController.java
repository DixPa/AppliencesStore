package com.dixpa.store.Cart.controller;

import com.dixpa.store.Cart.dto.ApiResponse;
import com.dixpa.store.Cart.dto.CartResponseDTO;
import com.dixpa.store.Cart.dto.CartRequestDTO;
import com.dixpa.store.Cart.dto.ItemCartDTO;
import com.dixpa.store.Cart.model.Cart;
import com.dixpa.store.Cart.service.ICartService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/cart")
public class CartController {

    private final ICartService service;

    @PostMapping("/add/items")
    public CartResponseDTO addItems(@RequestBody CartRequestDTO cartRequest){
        return service.decideCreatOrAdd(cartRequest);
    }
    @GetMapping("/{id}")
    public CartResponseDTO getCartByIdUser(@PathVariable Long id){
        return service.getCart(id);
    }

    @GetMapping("/get/{id}")
    public List<ItemCartDTO> getItemsCardForUser(@PathVariable Cart id){
        return service.getItemsCart(id);
    }

    @PutMapping("/edit")
    public CartResponseDTO editQuantityItems(@RequestBody CartRequestDTO cartRequest){
        return service.editQuantityOfProduct(cartRequest);
    }

    @DeleteMapping("/delete")
    public ResponseEntity<ApiResponse> deleteItem(@RequestBody CartRequestDTO cartRequest){
        service.deleteItem(cartRequest);
        return new ResponseEntity<>(new ApiResponse("Delete"), HttpStatus.ACCEPTED);
    }

}
