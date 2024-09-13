package com.data.tallermodelodatos.repositories;

import com.data.tallermodelodatos.entities.Aeropuerto;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface AeropuertoRepository extends JpaRepository<Aeropuerto, Long> {
    List<Aeropuerto> findByNombre(String nombre);
    List<Aeropuerto> findByIdIn(List<Long> ids);
    Aeropuerto findByCiudad(String ciudad);
    Aeropuerto findByPais(String pais);
}
