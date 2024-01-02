package com.dixonpa.store.users.service;

import com.dixonpa.store.users.dto.UserRequestDTO;
import com.dixonpa.store.users.dto.UserResponseDTO;
import com.dixonpa.store.users.dto.UserUpdateDTO;
import com.dixonpa.store.users.exceptions.UserExistException;
import com.dixonpa.store.users.model.Users;
import com.dixonpa.store.users.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.net.URLDecoder;
import java.nio.charset.StandardCharsets;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class UserService implements IUserService {

    private final UserRepository repository;
    @Override
    public UserResponseDTO saveUser(UserRequestDTO userRequestDTO) {
        PasswordEncoder passCrypt = new BCryptPasswordEncoder();
        var user = repository.findByEmail(userRequestDTO.email());
        if(user.isPresent()) throw new UserExistException();
        else {
            String userPassword = passCrypt.encode(userRequestDTO.password());
            Users newUser = Users.builder()
                    .email(userRequestDTO.email())
                    .password(userPassword)
                    .name(userRequestDTO.name())
                    .lastname(userRequestDTO.lastname())
                    .build();
            repository.save(newUser);
            return getUserById(newUser.getId());
        }
    }

    @Override
    public void deleteUser(Long id) {
        repository.deleteById(id);
    }

    @Override
    public UserResponseDTO updateUser(Long id, UserUpdateDTO userUpdateDTO) {
        PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

        var userUpdate = repository.findById(id).orElseThrow();
        if(userUpdateDTO.email() !=null) userUpdate.setEmail(userUpdate.getEmail());
        if(userUpdateDTO.password() !=null){
            String newPassword = passwordEncoder.encode(userUpdateDTO.password());
            userUpdate.setPassword(newPassword);
        }
        if(userUpdateDTO.name() !=null) userUpdate.setName(userUpdate.getName());
        if(userUpdateDTO.lastname() !=null) userUpdate.setLastname(userUpdate.getLastname());
        repository.save(userUpdate);

        return getUserById(userUpdate.getId());
    }

    @Override
    public UserResponseDTO getUserById(Long id) {
        var user = repository.findById(id).orElseThrow();
        return UserResponseDTO.builder()
                .id(user.getId())
                .email(user.getEmail())
                .name(user.getName())
                .lastname(user.getLastname())
                .build();
    }

    @Override
    public UserResponseDTO getUserByEmail(String email) {
        String emailDecode = URLDecoder.decode(email, StandardCharsets.UTF_8);
        var user = repository.findByEmail(email).orElseThrow();
        return getUserById(user.getId());
    }

    @Override
    public List<UserResponseDTO> getAllUser() {
        List<Users> listUser = repository.findAll();
        return listUser.stream()
                .map(users -> UserResponseDTO.builder()
                        .id(users.getId())
                        .email(users.getEmail())
                        .name(users.getName())
                        .lastname(users.getLastname())
                        .build()).collect(Collectors.toList());
    }
}
