package com.data.tallermodelodatos.services;

import com.data.tallermodelodatos.dto.UserDto;
import java.util.Optional;

public interface UserService {
    UserDto updateUser(Long id, UserDto userDto);
    void deleteUser(Long id);
    Optional<UserDto> findByUsername(String username);
    boolean isUserAuthorized(Long id, String username);
    Optional<UserDto> findById(Long id);
}