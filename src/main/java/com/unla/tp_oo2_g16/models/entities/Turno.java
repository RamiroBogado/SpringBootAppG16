package com.unla.tp_oo2_g16.models.entities;

import lombok.*;
import java.time.LocalDateTime;
import jakarta.persistence.*;

@Entity
@Table(name = "turnos")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Turno {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_turno")
    private Integer idTurno;
    
    @ManyToOne
    @JoinColumn(name = "id_empleado", nullable = false)
    private Empleado empleado;
    
    @ManyToOne
    @JoinColumn(name = "id_cliente", nullable = false)
    private Cliente cliente;
    
    @ManyToOne
    @JoinColumn(name = "id_servicio", nullable = false)
    private Servicio servicio;
    
    @Column(name = "fecha_hora", nullable = false)
    private LocalDateTime fechaHora;
    
    @Column(name = "observaciones", columnDefinition = "TEXT")
    private String observaciones;
    
    @Column(name = "estado", nullable = false, length = 20)
    private String estado; // Ej: "PENDIENTE", "CONFIRMADO", "CANCELADO", "COMPLETADO"
    
    @Column(name = "codigo_a", nullable = false, length = 20)
    private String codigoA;
    
    // Constructor sin ID
    public Turno(Empleado empleado, Cliente cliente, Servicio servicio, 
                LocalDateTime fechaHora, String observaciones, String estado) {
        this.empleado = empleado;
        this.cliente = cliente;
        this.servicio = servicio;
        this.fechaHora = fechaHora;
        this.observaciones = observaciones;
        this.estado = estado;
    }
}