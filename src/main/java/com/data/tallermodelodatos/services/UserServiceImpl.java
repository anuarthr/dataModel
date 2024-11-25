package com.data.tallermodelodatos.services;

import com.data.tallermodelodatos.dto.UserDto;
import com.data.tallermodelodatos.entities.User;
import com.data.tallermodelodatos.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    private static final Logger logger = LoggerFactory.getLogger(UserServiceImpl.class);

    @Override
    public UserDto updateUser(Long id, UserDto userDto) {
        return userRepository.findById(id)
                .map(existingUser -> {
                    existingUser.setEmail(userDto.email());
                    existingUser.setPassword(passwordEncoder.encode(userDto.password()));
                    existingUser.setUsername(userDto.username());
                    existingUser.setNombre(userDto.nombre());
                    existingUser.setApellido(userDto.apellido());
                    existingUser.setDireccion(userDto.direccion());
                    existingUser.setTelefono(userDto.telefono());
                    User updatedUser = userRepository.save(existingUser);
                    return convertToDto(updatedUser);
                })
                .orElseThrow(() -> {
                    logger.error("User not found with ID: {}", id);
                    return new RuntimeException("User not found");
                });
    }

    @Override
    public void deleteUser(Long id) {
        userRepository.deleteById(id);
    }

    @Override
    public Optional<UserDto> findByUsername(String username) {
        return userRepository.findByUsername(username).map(this::convertToDto);
    }

    @Override
    public boolean isUserAuthorized(Long id, String username) {
        return false;
    }

    @Override
    public Optional<UserDto> findById(Long id) {
        logger.info("Buscando usuario con id: {}", id);
        return userRepository.findById(id).map(this::convertToDto);
    }

    @Override
    public boolean existsByUsername(String username) {
        return userRepository.existsByUsername(username);
    }

    @Override
    public boolean existsByEmail(String email) {
        return userRepository.existsByEmail(email);
    }

    @Override
    public void saveUser(User user) {
        userRepository.save(user);
    }

    private UserDto convertToDto(User user) {
        return new UserDto(
                user.getId(),
                user.getUsername(),
                user.getEmail(),
                user.getPassword(),
                user.getRoles(),
                user.getNombre(),
                user.getApellido(),
                user.getDireccion(),
                user.getTelefono()
        );
    }
}