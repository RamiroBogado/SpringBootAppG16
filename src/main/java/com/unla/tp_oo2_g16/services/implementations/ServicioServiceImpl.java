package com.unla.tp_oo2_g16.services.implementations;

import lombok.RequiredArgsConstructor;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.unla.tp_oo2_g16.models.dto.responses.ServicioResponsesDTO;
import com.unla.tp_oo2_g16.models.entities.Servicio;
import com.unla.tp_oo2_g16.repositories.ServicioRepository;
import com.unla.tp_oo2_g16.services.interfaces.ServicioServiceInterface;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ServicioServiceImpl implements ServicioServiceInterface {
    
    private final ServicioRepository servicioRepository;
    
    @Autowired
    private ModelMapper modelMapper;
    
    @Override
    public List<ServicioResponsesDTO> findAll() {
        
		return servicioRepository.findAll()
                .stream() 
                .map(servicio -> modelMapper.map(servicio, ServicioResponsesDTO.class))
                .collect(Collectors.toList());   
    }
    
    @Override
    public Servicio findById(Integer id) {
        return servicioRepository.findById(id).orElse(null);
    }
    
    @Override
    public Servicio save(Servicio servicio) {
        return servicioRepository.save(servicio);
    }
    
    @Override
    public void deleteById(Integer id) {
        servicioRepository.deleteById(id);
    }
    
}
