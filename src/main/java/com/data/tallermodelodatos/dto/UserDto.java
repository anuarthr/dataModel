package com.data.tallermodelodatos.dto;

public record UserDto(
        Long id,
        String username,
        String email,
        String password,
        java.util.Set<String> roles) {}