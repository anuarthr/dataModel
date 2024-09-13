package com.data.tallermodelodatos.repositories;

import com.data.tallermodelodatos.entities.Aerolinea;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface AerolineaRepository extends JpaRepository<Aerolinea, Long> {
    List<Aerolinea> findByNombre(String nombre);
    List<Aerolinea> findByIdIn(List<Long> ids);
    Aerolinea findByCodigoAerolinea(Long codigo);
    Aerolinea findByPaisDeOrigen(String paisDeOrigen);
}
