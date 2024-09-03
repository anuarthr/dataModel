package com.data.entities;

import jakarta.persistence.*;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "escalas")
@Data
public class Escala {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idEscala;

    @ManyToMany
    @JoinTable(
            name = "escalas_de_los_vuelos",
            joinColumns = @JoinColumn(name = "escala_id", referencedColumnName = "idEscala"),
            inverseJoinColumns = @JoinColumn(name = "vuelo_id", referencedColumnName = "idVuelo")
    )
    private List<Vuelo> vuelos = new ArrayList<>();

    @OneToOne(mappedBy = "escalas")
    private Aeropuerto aeropuerto;

    private float tiempoDeEscala;

}
