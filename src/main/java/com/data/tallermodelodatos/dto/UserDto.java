package com.data.tallermodelodatos.dto;

import java.util.Set;

public record UserDto(
        Long id,
        String username,
        String email,
        String password,
        Set<String> roles,
        String nombre,
        String apellido,
        String direccion,
        String telefono
) {}