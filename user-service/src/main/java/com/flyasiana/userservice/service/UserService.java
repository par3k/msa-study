package com.flyasiana.userservice.service;

import com.flyasiana.userservice.dto.UserDto;
import com.flyasiana.userservice.jpa.UserEntity;

public interface UserService {
    UserDto createUser(UserDto userDto);
    UserDto getUserById(String userId);
    public Iterable<UserEntity> getUserByAll();
}
