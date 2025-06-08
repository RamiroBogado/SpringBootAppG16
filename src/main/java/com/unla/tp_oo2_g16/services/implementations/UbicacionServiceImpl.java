package com.unla.tp_oo2_g16.services.implementations;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.unla.tp_oo2_g16.models.dto.responses.UbicacionResponsesDTO;
import com.unla.tp_oo2_g16.models.entities.Ubicacion;
import com.unla.tp_oo2_g16.repositories.UbicacionRepository;
import com.unla.tp_oo2_g16.services.interfaces.UbicacionServiceInterface;

@Service
public class UbicacionServiceImpl implements UbicacionServiceInterface {

    @Autowired
    private UbicacionRepository ubicacionRepository;
    
    @Autowired
    private ModelMapper modelMapper;

    @Override
    public List<UbicacionResponsesDTO> findAll() {
        return ubicacionRepository.findAll()
                .stream()
                .map(ubicacion -> modelMapper.map(ubicacion, UbicacionResponsesDTO.class))
                .collect(Collectors.toList());
    }

    @Override
    public Ubicacion findById(Integer id) {
        return ubicacionRepository.findById(id).orElse(null);
    }

    @Override
    public Ubicacion save(Ubicacion ubicacion) {
        return ubicacionRepository.save(ubicacion);
    }

    @Override
    public void deleteById(Integer id) {
        ubicacionRepository.deleteById(id);
    }

}
