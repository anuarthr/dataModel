package com.data.tallermodelodatos.repositories;

import com.data.tallermodelodatos.entities.Pasajero;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PasajeroRepository extends JpaRepository<Pasajero, Long> {
    List<Pasajero> findByIdIn(List<Long> ids);
    Pasajero findByPasaporteIn(Long pasaporte);
    List<Pasajero> findAllByNombre(String nombre);
    List<Pasajero> findAllByApellido(String apellido);
    List<Pasajero> findAllByNacionalidad(String nacionalidad);
}
