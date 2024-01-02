package com.dixonpa.store.users.dto;

import jakarta.validation.constraints.Email;

public record UserUpdateDTO (
        @Email
        String email,
        String password,
        String name,
        String lastname
) {
}
