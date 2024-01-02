package com.dixpa.store.Cart.service;

import com.dixpa.store.Cart.dto.CartResponseDTO;
import com.dixpa.store.Cart.dto.CartRequestDTO;
import com.dixpa.store.Cart.dto.ItemCartDTO;
import com.dixpa.store.Cart.model.Cart;

import java.util.List;

public interface ICartService {
    CartResponseDTO decideCreatOrAdd(CartRequestDTO cartRequestDTO);
    CartResponseDTO saveCart(CartRequestDTO cartRequestDTO);
    CartResponseDTO getCart (Long id);
    void deleteCart(Long idUser);
    CartResponseDTO addItemToCart(CartRequestDTO cartRequestDTO);
    CartResponseDTO editQuantityOfProduct(CartRequestDTO cartRequest);
    void deleteItem(CartRequestDTO cartRequestDTO);
    List<ItemCartDTO> getItemsCart(Cart idCart);
}
