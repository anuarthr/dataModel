package com.data.tallermodelodatos.services;

import com.data.tallermodelodatos.dto.AerolineaMapper;
import com.data.tallermodelodatos.dto.AerolineaDto;
import com.data.tallermodelodatos.repositories.AerolineaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service

public class AerolineaServiceImpl implements AerolineaService {
    
    private AerolineaRepository aerolineaRepository;

    @Autowired
    private final AerolineaMapper aerolineaMapper;
    public AerolineaServiceImpl(AerolineaRepository aerolineaRepository, AerolineaMapper aerolineaMapper) {
        this.aerolineaRepository = aerolineaRepository;
        this.aerolineaMapper = aerolineaMapper;
    }

    @Override
    public AerolineaDto guardarAerolinea(AerolineaDto aerolinea) {return aerolineaMapper.toDto(aerolineaRepository.save(aerolineaMapper.toAerolinea(aerolinea)));}

    @Override
    public Optional<AerolineaDto> buscarAerolineaPorId(Long id) {
        return aerolineaRepository.findById(id).map(aerolinea -> aerolineaMapper.toDto(aerolinea));
    }

    @Override
    public List<AerolineaDto> buscarAerolineas() {
        List<AerolineaDto> aerolineas = new ArrayList<>();
        return aerolineaRepository.findAll().forEach(aerolinea -> aerolineaMapper.toDto());
    }
    @Override
    public List<AerolineaDto> buscarAerolineasPorNombre(String nombre) {return aerolineaRepository.findByNombre(nombre);}

    @Override
    public List<AerolineaDto> buscarAerolineasPorIds(List<Long> ids) {return aerolineaRepository.findByIdIn(ids);}

    @Override
    public AerolineaDto buscarAerolineaPorCodigo(Long codigo) {return aerolineaRepository.findByCodigoAerolinea(codigo);}

    @Override
    public List<AerolineaDto> buscarAerolineasPorPaisDeOrigen(String paisDeOrigen) {return aerolineaRepository.findByPaisDeOrigen(paisDeOrigen);}

    @Override
    public AerolineaDto actualizarAerolinea(AerolineaDto aerolinea) {return aerolineaRepository.save(aerolinea);}

    @Override
    public Optional<AerolineaDto> actualizarAerolinea(Long id, AerolineaDto aerolinea) {
        return aerolineaRepository.findById(id).map(oldAerolinea -> {
            oldAerolinea.setNombre(aerolinea.getNombre());
            oldAerolinea.setCodigoAerolinea(aerolinea.getCodigoAerolinea());
            oldAerolinea.setPaisDeOrigen(aerolinea.getPaisDeOrigen());
            oldAerolinea.setVuelos(aerolinea.getVuelos());
            return aerolineaRepository.save(oldAerolinea);
        });
    }

    @Override
    public void eliminarAerolinea(Long id) {
        aerolineaRepository.deleteById(id);
    }
}
