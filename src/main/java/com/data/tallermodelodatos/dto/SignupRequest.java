package com.data.tallermodelodatos.dto;

public record SignupRequest(
        String username,
        String nombre,
        String apellido,
        String direccion,
        String telefono,
        String email,
        String password
) {}