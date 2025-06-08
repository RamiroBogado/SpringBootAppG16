package com.unla.tp_oo2_g16.models.entities;

import lombok.*;
import java.time.LocalTime;
import jakarta.persistence.*;

@Entity
@Table(name = "disponibilidades")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Disponibilidad {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_disponibilidad")
    private Integer idDisponibilidad;
    
    @Column(name = "dia", nullable = false, length = 20)
    private String dia; // Ej: "LUNES", "MARTES", etc.
    
    @Column(name = "hora_inicio", nullable = false)
    private LocalTime horarioInicio;
    
    @Column(name = "hora_fin", nullable = false)
    private LocalTime horarioFin;
    
    @ManyToOne
    @JoinColumn(name = "id_servicio", nullable = false)
    private Servicio servicio; // Cambiado de 'service' a 'servicio'

    // Constructor actualizado
    public Disponibilidad(String dia, LocalTime horarioInicio, LocalTime horarioFin, Servicio servicio) {
        this.dia = dia;
        this.horarioInicio = horarioInicio;
        this.horarioFin = horarioFin;
        this.servicio = servicio;
    }
}