package com.unla.tp_oo2_g16.repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import com.unla.tp_oo2_g16.models.entities.Localidad;

@Repository
public interface LocalidadRepository extends CrudRepository<Localidad, Integer> {
}
