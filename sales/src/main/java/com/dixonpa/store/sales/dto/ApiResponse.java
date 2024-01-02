package com.dixonpa.store.sales.dto;

import lombok.Data;
import lombok.NoArgsConstructor;
import java.util.HashMap;
import java.util.Map;

@Data
@NoArgsConstructor
public class ApiResponse {

    private String message;

    private static final Map<String, String> defaultMessage = new HashMap<>();
    static{
        defaultMessage.put("Create","Sale successfully created");
        defaultMessage.put("Delete","Sale successfully removed");
    }
    public ApiResponse (String action){
        this.message = defaultMessage.get(action);
    }
}
