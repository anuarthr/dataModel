package com.data.tallermodelodatos.services;

import com.data.tallermodelodatos.dto.AerolineaMapper;
import com.data.tallermodelodatos.dto.AerolineaDto;
import com.data.tallermodelodatos.entities.Aerolinea;
import com.data.tallermodelodatos.repositories.AerolineaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class AerolineaServiceImpl implements AerolineaService {
    @Autowired
    private AerolineaRepository aerolineaRepository;
    private final AerolineaMapper aerolineaMapper;

    public AerolineaServiceImpl(AerolineaRepository aerolineaRepository, AerolineaMapper aerolineaMapper) {
        this.aerolineaRepository = aerolineaRepository;
        this.aerolineaMapper = aerolineaMapper;
    }

    @Override
    public AerolineaDto guardarAerolinea(AerolineaDto aerolinea) {
        Aerolinea savedAerolinea = aerolineaRepository.save(aerolineaMapper.aerolineaDtoToAerolinea(aerolinea));
        return aerolineaMapper.aerolineaToAerolineaDto(savedAerolinea);
    }

    @Override
    public Optional<AerolineaDto> buscarAerolineaPorId(Long id) {
        return aerolineaRepository.findById(id).map(
                aerolinea -> aerolineaMapper.aerolineaToAerolineaDto(aerolinea));
    }

    @Override
    public List<AerolineaDto> buscarAerolineas() {
        List<AerolineaDto> aerolineas = new ArrayList<>();
        aerolineaRepository.findAll().forEach(
                aerolinea -> aerolineas.add(aerolineaMapper.aerolineaToAerolineaDto(aerolinea)));
        return aerolineas;
    }

    @Override
    public List<AerolineaDto> buscarAerolineasPorNombre(String nombre) {
        List<AerolineaDto> aerolineas = new ArrayList<>();
        aerolineaRepository.findAllByNombre(nombre).forEach(
                aerolinea -> aerolineas.add(aerolineaMapper.aerolineaToAerolineaDto(aerolinea)));
        return aerolineas;
    }

    @Override
    public List<AerolineaDto> buscarAerolineasPorIds(List<Long> ids) {
        List<AerolineaDto> aerolineas = new ArrayList<>();
        aerolineaRepository.findByIdAerolineaIn(ids).forEach(
                aerolinea -> aerolineas.add(aerolineaMapper.aerolineaToAerolineaDto(aerolinea)));
        return aerolineas;
    }

    @Override
    public Optional<AerolineaDto> buscarAerolineaPorCodigo(Long codigo) {
        return aerolineaRepository.findByCodigoAerolinea(codigo)
                .map(aerolinea -> aerolineaMapper.aerolineaToAerolineaDto(aerolinea));
    }

    @Override
    public List<AerolineaDto> buscarAerolineasPorPaisDeOrigen(String paisDeOrigen) {
        List<AerolineaDto> aerolineas = new ArrayList<>();
        aerolineaRepository.findAllByPaisDeOrigen(paisDeOrigen).forEach(
                aerolinea -> aerolineas.add(aerolineaMapper.aerolineaToAerolineaDto(aerolinea)));
        return aerolineas;
    }

    @Override
    public Optional<AerolineaDto> actualizarAerolinea(Long id, AerolineaDto aerolinea) {
        return aerolineaRepository.findById(id).map(oldAerolinea -> {
            oldAerolinea.setNombre(aerolinea.nombre());
            oldAerolinea.setCodigoAerolinea(aerolinea.codigoAerolinea());
            oldAerolinea.setPaisDeOrigen(aerolinea.paisDeOrigen());
            return aerolineaMapper.aerolineaToAerolineaDto(aerolineaRepository.save(oldAerolinea));
        });
    }

    @Override
    public void eliminarAerolinea(Long id) {
        aerolineaRepository.deleteById(id);
    }
}