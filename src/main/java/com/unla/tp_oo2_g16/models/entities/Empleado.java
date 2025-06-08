package com.unla.tp_oo2_g16.models.entities;

import lombok.*;
import jakarta.persistence.*;

@Entity
@Table(name = "empleados")
@Data
@EqualsAndHashCode(callSuper = true)
@NoArgsConstructor
public class Empleado extends Persona {
    
    @Column(name = "legajo", unique = true)
    private String legajo;

	public Empleado(String nombre, String apellido, String dni, String legajo) {
		super(nombre, apellido, dni);
		this.legajo = legajo;
	}
    
    
    
}
