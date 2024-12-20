package com.data.tallermodelodatos.dto;

import java.time.LocalDate;
import java.util.List;

public record ReservaDto(Long idReserva, LocalDate fechaDeReserva, Integer numeroDePasajeros, Long idCliente, List<PasajeroDto> pasajeros) {
}