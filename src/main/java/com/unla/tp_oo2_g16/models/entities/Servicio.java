package com.unla.tp_oo2_g16.models.entities;

import lombok.*;

import java.util.HashSet;
import java.util.Set;

import jakarta.persistence.*;

@Entity
@Table(name = "servicios")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Servicio {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_servicio")
    private Integer id;
    
    @Column(name = "nombre", nullable = false, length = 100)
    private String nombre;
    
    @Column(name = "descripcion", columnDefinition = "TEXT")
    private String descripcion;
    
    @Column(name = "duracion_minutos", nullable = false)
    private Integer duracion; // en minutos
    
    // Relación ManyToMany con Ubicacion
    @ManyToMany(cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    @JoinTable(
        name = "servicio_ubicacion",
        joinColumns = @JoinColumn(name = "id_servicio"),
        inverseJoinColumns = @JoinColumn(name = "id_ubicacion")
    )
    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    private Set<Ubicacion> ubicaciones = new HashSet<>();
    
    @OneToMany(mappedBy = "servicio", cascade = CascadeType.ALL, orphanRemoval = true)
    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    private Set<Disponibilidad> disponibilidades = new HashSet<>();
    
    // Constructor sin colecciones
    public Servicio(String nombre, String descripcion, Integer duracion) {
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.duracion = duracion;
    }
    
    // Métodos helper para la relación ManyToMany
    public void addUbicacion(Ubicacion ubicacion) {
        this.ubicaciones.add(ubicacion);
        ubicacion.getServicios().add(this);
    }
    
    public void removeUbicacion(Ubicacion ubicacion) {
        this.ubicaciones.remove(ubicacion);
        ubicacion.getServicios().remove(this);
    }

	public Servicio(String nombre, String descripcion, Integer duracion, Set<Ubicacion> ubicaciones,
			Set<Disponibilidad> disponibilidades) {
		super();
		this.nombre = nombre;
		this.descripcion = descripcion;
		this.duracion = duracion;
		this.ubicaciones = ubicaciones;
		this.disponibilidades = disponibilidades;
	}
    
    
}