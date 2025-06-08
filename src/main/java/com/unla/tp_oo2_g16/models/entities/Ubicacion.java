package com.unla.tp_oo2_g16.models.entities;

import java.util.HashSet;
import java.util.Set;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "ubicaciones")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Ubicacion {
 
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_ubicacion")
    private Integer id;
    
    @Column(name = "direccion")
    private String direccion;
    
    @ManyToOne
    @JoinColumn(name = "id_localidad")
    private Localidad localidad;
    
    // Relación ManyToMany con Servicio
    @ManyToMany(mappedBy = "ubicaciones")
    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    private Set<Servicio> servicios = new HashSet<>();
    
}
