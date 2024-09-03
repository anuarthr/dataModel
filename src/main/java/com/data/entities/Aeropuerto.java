package com.data.entities;

import jakarta.persistence.*;
import lombok.Data;
import java.io.Serializable;

@Entity
@Table(name = "aeropuertos")
@Data
public class Aeropuerto implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
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
