package com.data.tallermodelodatos.services;

import com.data.tallermodelodatos.dto.VueloDto;
import com.data.tallermodelodatos.dto.VueloMapper;
import com.data.tallermodelodatos.entities.Vuelo;
import com.data.tallermodelodatos.repositories.VueloRepository;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class VueloServiceImpl implements VueloService {

    private final VueloRepository vueloRepository;
    private final VueloMapper vueloMapper;

    // Constructor inyectando el vueloMapper
    public VueloServiceImpl(VueloRepository vueloRepository, VueloMapper vueloMapper) {
        this.vueloRepository = vueloRepository;
        this.vueloMapper = vueloMapper;
    }

    @Override
    public List<VueloDto> buscarVuelos() {
        return vueloRepository.findAll().stream()
                .map(vueloMapper::vueloToVueloDto)
                .toList();
    }

    @Override
    public Optional<VueloDto> buscarVueloPorId(Long id) {
        return vueloRepository.findById(id)
                .map(vueloMapper::vueloToVueloDto);
    }

    @Override
    public VueloDto guardarVuelo(VueloDto vueloDto) {
        Vuelo vuelo = vueloMapper.vueloDtoToVuelo(vueloDto);
        Vuelo vueloGuardado = vueloRepository.save(vuelo);
        return vueloMapper.vueloToVueloDto(vueloGuardado);
    }

    @Override
    public Optional<VueloDto> actualizarVuelo(Long id, VueloDto vueloToUpdateDto) {
        return vueloRepository.findById(id).map(vuelo -> {
            Vuelo updatedVuelo = vueloMapper.vueloDtoToVuelo(vueloToUpdateDto);
            vuelo.setOrigen(updatedVuelo.getOrigen());
            vuelo.setDestino(updatedVuelo.getDestino());
            vuelo.setFechaDeSalida(updatedVuelo.getFechaDeSalida());
            vuelo.setHoraDeSalida(updatedVuelo.getHoraDeSalida());
            vuelo.setDuracion(updatedVuelo.getDuracion());
            vuelo.setCapacidad(updatedVuelo.getCapacidad());
            vuelo.setReservas(updatedVuelo.getReservas());
            Vuelo vueloGuardado = vueloRepository.save(vuelo);
            return vueloMapper.vueloToVueloDto(vueloGuardado);
        });
    }

    @Override
    public List<VueloDto> buscarVuelosPorIds(List<Long> ids) {
        return vueloRepository.findAllById(ids).stream()
                .map(vueloMapper::vueloToVueloDto)
                .toList();
    }

    @Override
    public List<VueloDto> buscarVuelosPorOrigen(String origen) {
        return vueloRepository.findByOrigen(origen).stream()
                .map(vueloMapper::vueloToVueloDto)
                .toList();
    }

    @Override
    public List<VueloDto> buscarVuelosPorDestino(String destino) {
        return vueloRepository.findByDestino(destino).stream()
                .map(vueloMapper::vueloToVueloDto)
                .toList();
    }

    @Override
    public void deleteVuelo(Long id) {
        vueloRepository.deleteById(id);
    }
}
