package com.dixonpa.store.sales.service;

import com.dixonpa.store.sales.dto.SalesRequestDTO;
import com.dixonpa.store.sales.dto.SalesResponseDTO;

public interface ISaleService {
    SalesResponseDTO saveSale(SalesRequestDTO salesRequestDTO);
    SalesResponseDTO getSaleByIdUser(Long id_user);
    void deleteSale(Long id_user);
}
