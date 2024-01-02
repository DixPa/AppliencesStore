package com.dixonpa.store.users.service;

import com.dixonpa.store.users.dto.UserRequestDTO;
import com.dixonpa.store.users.dto.UserResponseDTO;
import com.dixonpa.store.users.dto.UserUpdateDTO;

import java.util.List;

public interface IUserService {

    UserResponseDTO saveUser(UserRequestDTO userRequestDTO);
    void deleteUser (Long id);
    UserResponseDTO updateUser (Long id, UserUpdateDTO userUpdateDTO);

    UserResponseDTO getUserById(Long id);
    UserResponseDTO getUserByEmail(String email);
    List<UserResponseDTO> getAllUser();

}
