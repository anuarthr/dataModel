package com.data.tallermodelodatos.repositories;

import com.data.tallermodelodatos.entities.Pasajero;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PasajeroRepository extends JpaRepository<Pasajero, Long> {
    List<Pasajero> findByNombre(String nombre);
    List<Pasajero> findByIdIn(List<Long> ids);
    List<Pasajero> findByApellido(String apellido);
    Pasajero findByPasaporte(Long pasaporte);
    List<Pasajero> findByNacionalidad(String nacionalidad);
}
