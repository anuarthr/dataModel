package com.data.entities;

import jakarta.persistence.*;
import lombok.Data;
import java.io.Serializable;
import java.time.LocalDate;

@Entity
@Table(name = "reservas")
@Data
public class Reserva implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idReserva;

    @ManyToOne
    @JoinColumn(name = "idCliente")
    private Cliente cliente;

    @ManyToOne
    @JoinColumn(name = "idVuelo")
    private Vuelo vuelo;

    @Column(name = "fecha_reserva")
    private LocalDate fechaDeReserva;

    @Column(name = "numero_pasajeros")
    private Integer numeroDePasajeros;
}
