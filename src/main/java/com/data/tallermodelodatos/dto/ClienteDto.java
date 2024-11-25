package com.data.tallermodelodatos.dto;

public record ClienteDto(Long idCliente, String nombre, String apellido, String direccion,
                         String telefono, String email, String password, String username) {
}