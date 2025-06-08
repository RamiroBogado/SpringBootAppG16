package com.unla.tp_oo2_g16.services.interfaces;

import java.time.LocalTime;
import java.util.List;
import com.unla.tp_oo2_g16.models.entities.Disponibilidad;

public interface DisponibilidadesServiceInterface {
	
    List<Disponibilidad> findAll();
    Disponibilidad findById(Integer id);
    Disponibilidad save(Disponibilidad disponibilidad);
    void deleteById(Integer id);
    
    List<LocalTime> findAllhorariosDisponibles();

}

