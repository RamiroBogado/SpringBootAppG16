package com.unla.tp_oo2_g16.services.interfaces;

import java.util.List;

import com.unla.tp_oo2_g16.models.dto.responses.UbicacionResponsesDTO;
import com.unla.tp_oo2_g16.models.entities.Ubicacion;

public interface UbicacionServiceInterface {

    List<UbicacionResponsesDTO> findAll();
    Ubicacion findById(Integer id);
    Ubicacion save(Ubicacion ubicacion);
    void deleteById(Integer id);

}
