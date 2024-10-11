package com.data.tallermodelodatos.repositories;

import com.data.tallermodelodatos.entities.Aeropuerto;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface AeropuertoRepository extends JpaRepository<Aeropuerto, Long> {
    List<Aeropuerto> findByIdIn(List<Long> ids);
    List<Aeropuerto> findAllByNombre(String nombre);
    List<Aeropuerto> findAllByCiudad(String ciudad);
    List<Aeropuerto> findAllByPais(String pais);
}
