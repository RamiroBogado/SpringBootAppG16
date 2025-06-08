package com.unla.tp_oo2_g16.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import com.unla.tp_oo2_g16.models.entities.Ubicacion;

public interface UbicacionRepository extends JpaRepository<Ubicacion, Integer> {
	
}
