package com.flyasiana.userservice.service;

import com.flyasiana.userservice.dto.UserDto;
import com.flyasiana.userservice.jpa.UserEntity;
import org.springframework.security.core.userdetails.UserDetailsService;

public interface UserService extends UserDetailsService {
    UserDto createUser(UserDto userDto);
    UserDto getUserById(String userId);
    Iterable<UserEntity> getUserByAll();

    UserDto getUserDetailsByEmail(String email);
}
