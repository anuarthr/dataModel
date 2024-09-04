package com.data.entities;

import jakarta.persistence.*;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@Entity
@Getter
@Setter
@Table(name = "aeropuertos")
@Data
public class Aeropuerto implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long idAeropuerto;

    @Column(nullable = false)
    private String nombre;

    @Column(nullable = false)
    private String ciudad;

    @Column(nullable = false)
    private String pais;

    @OneToOne(optional = false)
    @JoinColumn(name = "escala_id", referencedColumnName = "idEscala")
    private Escala escala;
}
