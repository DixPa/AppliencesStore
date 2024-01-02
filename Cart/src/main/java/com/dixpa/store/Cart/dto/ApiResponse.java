package com.dixpa.store.Cart.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.HashMap;
import java.util.Map;

@NoArgsConstructor
@Data
public class ApiResponse {

    private String message;

    private static final Map<String, String> defaultMessage = new HashMap<>();
    static {
        defaultMessage.put("Delete", "Item deleted successfully");
    }

    public ApiResponse (String action){
        this.message = defaultMessage.get(action);
    }

}
