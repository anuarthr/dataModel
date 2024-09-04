package com.data.entities;

import jakarta.annotation.Nullable;
import jakarta.persistence.*;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@Entity
@Getter
@Setter
@Table(name = "aerolineas")
@Data
public class Aerolinea implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long idAerolinea;

    @Column(nullable = false)
    private String nombre;

    @Column(nullable = false)
    private Long codigoAerolinea;

    @Column(nullable = false)
    private String paisDeOrigen;
}
