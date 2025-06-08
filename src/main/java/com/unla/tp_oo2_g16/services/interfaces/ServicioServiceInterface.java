package com.unla.tp_oo2_g16.services.interfaces;

import java.util.List;

import com.unla.tp_oo2_g16.models.dto.responses.ServicioResponsesDTO;
import com.unla.tp_oo2_g16.models.entities.Servicio;

public interface ServicioServiceInterface {
	
	List<ServicioResponsesDTO> findAll();
    Servicio findById(Integer id);
    Servicio save(Servicio servicio);
    void deleteById(Integer id);

}
