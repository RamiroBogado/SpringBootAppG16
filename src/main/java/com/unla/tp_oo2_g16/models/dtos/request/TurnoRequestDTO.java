package com.unla.tp_oo2_g16.models.dtos.request;

import java.time.LocalDateTime;

import com.unla.tp_oo2_g16.models.entities.Empleado;
import com.unla.tp_oo2_g16.models.entities.Servicio;
import com.unla.tp_oo2_g16.models.entities.Ubicacion;

import lombok.Data;

@Data
public class TurnoRequestDTO {
	
    private Integer idTurno;
    private Empleado empleado;
    private ClienteRequestDTO cliente;
    private Servicio servicio;
    private LocalDateTime fechaHora;
    private String observaciones;
    private String estado;
    private String codigoA;
    private Ubicacion ubicacion;

}
