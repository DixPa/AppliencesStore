package com.dixonpa.store.users.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.HashMap;
import java.util.Map;

@NoArgsConstructor
@Data
public class ApiResponse {

    private String message;

    private static final Map<String, String> defaultMessages = new HashMap<>();

    static {
        defaultMessages.put("create", "User create successful");
        defaultMessages.put("delete", "User delete successful");
        defaultMessages.put("deleteAll", "Users delete successful");
    }

    public ApiResponse (String action){
        this.message = defaultMessages.get(action);
    }
}
