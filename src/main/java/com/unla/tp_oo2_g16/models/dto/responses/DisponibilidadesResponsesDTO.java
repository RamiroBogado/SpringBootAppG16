package com.unla.tp_oo2_g16.models.dto.responses;

import java.time.LocalTime;
import com.unla.tp_oo2_g16.models.entities.Servicio;
import lombok.Data;

@Data
public class DisponibilidadesResponsesDTO {
	
    private String dia;
    private LocalTime horarioInicio;
    private LocalTime horarioFin;
    private Servicio servicio;

}
