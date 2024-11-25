package com.data.tallermodelodatos.repositories;

import com.data.tallermodelodatos.entities.Aerolinea;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;
import java.util.Optional;

public interface AerolineaRepository extends JpaRepository<Aerolinea, Long> {
    List<Aerolinea> findByIdAerolineaIn(List<Long> ids);
    List<Aerolinea> findAllByNombre(String nombre);
    Optional<Aerolinea> findByCodigoAerolinea(Long codigoAerolinea);
    List<Aerolinea> findAllByPaisDeOrigen(String paisDeOrigen);
}