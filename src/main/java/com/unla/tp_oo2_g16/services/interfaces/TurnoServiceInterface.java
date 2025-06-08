package com.unla.tp_oo2_g16.services.interfaces;

import java.time.LocalDateTime;
import java.util.List;

import com.unla.tp_oo2_g16.models.entities.Turno;

public interface TurnoServiceInterface {
	
	    List<Turno> findAll();
	    Turno findById(Integer id);
	    Turno save(Turno turno);
	    void deleteById(Integer id);
	    List<Turno> findByCliente(Integer clienteId);
	    List<Turno> findByEmpleado(Integer empleadoId);
	    List<Turno> findByServicio(Integer servicioId);
	    List<Turno> findByFechaBetween(LocalDateTime inicio, LocalDateTime fin);
	    List<Turno> findByEstado(String estado);
	    
	    Turno findByCodigoA(String codigo);
}