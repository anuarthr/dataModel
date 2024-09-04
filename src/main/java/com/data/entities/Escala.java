package com.data.entities;

import jakarta.persistence.*;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
@Table(name = "escalas")
@Data
public class Escala {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long idEscala;

    @ManyToOne
    private Vuelo vuelo;

    @OneToOne(mappedBy = "escalas")
    private Aeropuerto aeropuerto;

    private float tiempoDeEscala;

}
