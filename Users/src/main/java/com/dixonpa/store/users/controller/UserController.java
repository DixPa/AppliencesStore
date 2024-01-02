package com.dixonpa.store.users.controller;

import com.dixonpa.store.users.dto.ApiResponse;
import com.dixonpa.store.users.dto.UserRequestDTO;
import com.dixonpa.store.users.dto.UserResponseDTO;
import com.dixonpa.store.users.dto.UserUpdateDTO;
import com.dixonpa.store.users.service.IUserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/user")
@RequiredArgsConstructor
public class UserController {

    private final IUserService service;

    @PostMapping("/save")
    public UserResponseDTO saveUser (@RequestBody @Valid UserRequestDTO userRequestDTO){
        return service.saveUser(userRequestDTO);
    }

    @PutMapping("/update/{id}")
    public UserResponseDTO updateUser(@PathVariable Long id, @RequestBody @Valid UserUpdateDTO updateDTO){
        return service.updateUser(id, updateDTO);
    }
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<ApiResponse> deleteUser(@PathVariable Long id){
        service.deleteUser(id);
        return new ResponseEntity<>(new ApiResponse("delete"), HttpStatus.ACCEPTED);
    }
    @GetMapping("/{id}")
    public UserResponseDTO getUser(@PathVariable Long id){
        return service.getUserById(id);
    }
    @GetMapping("/email/{email}")
    public UserResponseDTO getUserByEmail (@PathVariable String email){
        return service.getUserByEmail(email);
    }

    @GetMapping
    public List<UserResponseDTO> getAllUser(){
        return service.getAllUser();
    }
}
