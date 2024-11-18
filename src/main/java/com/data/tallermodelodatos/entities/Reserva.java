package com.data.tallermodelodatos.entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
@Table(name = "reservas")
public class Reserva implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long idReserva;

    @ManyToOne
    @JoinColumn(name = "idCliente", nullable = false)
    private Cliente cliente;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(
            name = "rutas_vuelos",
            joinColumns = @JoinColumn(name = "reserva_id",referencedColumnName = "idReserva"),
            inverseJoinColumns = @JoinColumn(name = "vuelo_id", referencedColumnName = "idVuelo")
    )
    private List<Vuelo> vuelos = new ArrayList<>();

    @OneToMany(mappedBy = "reserva", fetch = FetchType.EAGER)
    private List<Pasajero> pasajeros = new ArrayList<>();

    @Column(name = "fecha_reserva")
    private LocalDate fechaDeReserva;

    @Column(name = "numero_pasajeros")
    private Integer numeroDePasajeros;

    public void addVuelo(Vuelo vuelo) {
        this.vuelos.add(vuelo);
    }
}
