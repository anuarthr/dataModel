package com.data.tallermodelodatos.repositories;

import com.data.tallermodelodatos.entities.Aerolinea;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface AerolineaRepository extends JpaRepository<Aerolinea, Long> {
    List<Aerolinea> findByNombre(String nombre);
    List<Aerolinea> findByIdIn(List<Long> ids);
    Optional<Aerolinea> findByCodigoAerolinea(Long codigo);
    List<Aerolinea> findByPaisDeOrigen(String paisDeOrigen);
    List<Aerolinea> findAllByNombre(String nombre);
}
