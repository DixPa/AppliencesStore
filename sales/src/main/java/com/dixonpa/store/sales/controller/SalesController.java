package com.dixonpa.store.sales.controller;

import com.dixonpa.store.sales.dto.ApiResponse;
import com.dixonpa.store.sales.dto.SalesRequestDTO;
import com.dixonpa.store.sales.dto.SalesResponseDTO;
import com.dixonpa.store.sales.service.ISaleService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/sale")
@RequiredArgsConstructor
public class SalesController {

    private final ISaleService service;

    @PostMapping("/save")
    public SalesResponseDTO saveSale(@RequestBody SalesRequestDTO salesRequestDTO){
        return service.saveSale(salesRequestDTO);
    }

    @GetMapping("/{idUser}")
    public SalesResponseDTO getSaleByIdUser(@PathVariable Long idUser){
        return service.getSaleByIdUser(idUser);
    }

    @DeleteMapping("/delete/{idUser}")
    public ResponseEntity<ApiResponse> deleteSale(@PathVariable Long idUser){
        service.deleteSale(idUser);
        return ResponseEntity.accepted().body(new ApiResponse("Delete"));
    }
}
