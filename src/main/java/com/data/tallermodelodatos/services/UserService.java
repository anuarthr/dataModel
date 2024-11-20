package com.data.tallermodelodatos.services;

import com.data.tallermodelodatos.dto.UserDto;

public interface UserService {
    UserDto updateUser(Long id, UserDto userDto);
    void deleteUser(Long id);
}