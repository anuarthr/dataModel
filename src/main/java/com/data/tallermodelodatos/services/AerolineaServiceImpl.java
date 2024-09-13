package com.data.tallermodelodatos.services;

import com.data.tallermodelodatos.entities.Aerolinea;
import com.data.tallermodelodatos.repositories.AerolineaRepository;

import java.util.List;
import java.util.Optional;

public class AerolineaServiceImpl implements AerolineaService {

    private AerolineaRepository aerolineaRepository;

    public AerolineaServiceImpl(AerolineaRepository aerolineaRepository) {
        this.aerolineaRepository = aerolineaRepository;
    }

    @Override
    public Aerolinea guardarAerolinea(Aerolinea aerolinea) {return aerolineaRepository.save(aerolinea);}

    @Override
    public Optional<Aerolinea> buscarAerolineaPorId(Long id) {return aerolineaRepository.findById(id);}

    @Override
    public List<Aerolinea> buscarAerolineas() {return aerolineaRepository.findAll();}

    @Override
    public List<Aerolinea> buscarAerolineasPorNombre(String nombre) {return aerolineaRepository.findByNombre(nombre);}

    @Override
    public List<Aerolinea> buscarAerolineasPorIds(List<Long> ids) {return aerolineaRepository.findByIdIn(ids);}

    @Override
    public Aerolinea buscarAerolineaPorCodigo(Long codigo) {return aerolineaRepository.findByCodigoAerolinea(codigo);}

    @Override
    public Aerolinea buscarAerolineaPorPaisDeOrigen(String paisDeOrigen) {return aerolineaRepository.findByPaisDeOrigen(paisDeOrigen);}

    @Override
    public Aerolinea actualizarAerolinea(Aerolinea aerolinea) {return aerolineaRepository.save(aerolinea);}

    @Override
    public Optional<Aerolinea> actualizarAerolinea(Long id, Aerolinea aerolinea) {
        return aerolineaRepository.findById(id).map(oldAerolinea -> {
            oldAerolinea.setNombre(aerolinea.getNombre());
            oldAerolinea.setCodigoAerolinea(aerolinea.getCodigoAerolinea());
            oldAerolinea.setPaisDeOrigen(aerolinea.getPaisDeOrigen());
            oldAerolinea.setVuelos(aerolinea.getVuelos());
            return aerolineaRepository.save(oldAerolinea);
        });
    }
}
