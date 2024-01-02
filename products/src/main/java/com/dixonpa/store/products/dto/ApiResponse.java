package com.dixonpa.store.products.dto;

import lombok.*;

import java.util.HashMap;
import java.util.Map;

@NoArgsConstructor
@Data
public class ApiResponse {
    private String message;

    private static final Map<String, String> defaultMessages = new HashMap<>();
    static {
        defaultMessages.put("create", "Product create successful");
        defaultMessages.put("update", "Product update successful");
        defaultMessages.put("delete", "Product delete successful");
        defaultMessages.put("AllDelete", "Products delete successful");
    }
    public ApiResponse(String action){
        this.message = defaultMessages.getOrDefault(action, "Successful");
    }
}
