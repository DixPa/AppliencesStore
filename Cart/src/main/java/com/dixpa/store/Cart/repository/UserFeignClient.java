package com.dixpa.store.Cart.repository;

import com.dixpa.store.Cart.dto.UserDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "user", url = "http://localhost:9002/user/email")
public interface UserFeignClient {
    @GetMapping("/{email}")
    UserDTO getUserByEmail(@PathVariable String email);
}
