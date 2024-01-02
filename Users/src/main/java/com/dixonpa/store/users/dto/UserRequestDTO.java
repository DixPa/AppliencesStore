package com.dixonpa.store.users.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

public record UserRequestDTO (
        @Email
        @NotBlank
        String email,
        @NotBlank
        String password,
        String name,
        String lastname
) {
}
