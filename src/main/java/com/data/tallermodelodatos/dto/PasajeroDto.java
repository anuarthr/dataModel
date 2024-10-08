package com.data.tallermodelodatos.dto;

public record PasajeroDto(Long idPasajero, String nombre, String apellido, String pasaporte,
                          String nacionalidad, ReservaDto reserva) {
}
