package com.data.tallermodelodatos.services;

import com.data.tallermodelodatos.dto.UserDto;
import com.data.tallermodelodatos.entities.User;
import com.data.tallermodelodatos.dto.UserMapper;
import com.data.tallermodelodatos.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private UserMapper userMapper;

    @Override
    public UserDto updateUser(Long id, UserDto userDto) {
        User user = userRepository.findById(id).orElseThrow(() -> new RuntimeException("User not found"));
        user.setUsername(userDto.username());
        user.setPassword(userDto.password());
        user.setEmail(userDto.email());
        User updatedUser = userRepository.save(user);
        return userMapper.userToUserDto(updatedUser);
    }

    @Override
    public void deleteUser(Long id) {
        userRepository.deleteById(id);
    }

    @Override
    public Optional<UserDto> findByUsername(String username) {
        return userRepository.findByUsername(username).map(userMapper::userToUserDto);
    }

    @Override
    public boolean isUserAuthorized(Long id, String username) {
        Optional<User> userOpt = userRepository.findById(id);
        return userOpt.isPresent() && userOpt.get().getUsername().equals(username);
    }


}