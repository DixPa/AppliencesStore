package com.dixonpa.store.sales.service;

import com.dixonpa.store.sales.dto.CartRequestDTO;
import com.dixonpa.store.sales.model.Sales;
import com.dixonpa.store.sales.dto.SalesRequestDTO;
import com.dixonpa.store.sales.dto.SalesResponseDTO;
import com.dixonpa.store.sales.repository.CartFeignClient;
import com.dixonpa.store.sales.repository.SalesRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class SaleService implements ISaleService {

    private final SalesRepository repository;
    private final CartFeignClient cartClient;
    @Override
    public SalesResponseDTO saveSale(SalesRequestDTO salesRequestDTO) {
        CartRequestDTO cart = cartClient.getCartByUserId(salesRequestDTO.getId_user());
        Sales sale = Sales.builder()
                .id_user(cart.getId_user())
                .saleNumber(UUID.randomUUID().toString())
                .createdDate(LocalDateTime.now())
                .priceTotal(cart.getTotalPrice())
                .build();
        repository.save(sale);
        return getSaleByIdUser(sale.getId_user());
    }

    @Override
    public SalesResponseDTO getSaleByIdUser(Long id_user) {
        Sales sales = repository.findById_user(id_user).orElseThrow();
        return SalesResponseDTO.builder()
                .id_user(sales.getId_user())
                .saleNumber(sales.getSaleNumber())
                .createdDate(sales.getCreatedDate())
                .priceTotal(sales.getPriceTotal())
                .build();
    }
    @Override
    public void deleteSale(Long id_user) {
        Sales sale = repository.findById_user(id_user).orElseThrow();
        repository.deleteById(sale.getId());
    }
}
