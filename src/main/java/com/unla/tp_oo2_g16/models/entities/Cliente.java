package com.unla.tp_oo2_g16.models.entities;

import lombok.*;
import java.util.List;
import jakarta.persistence.*;

@Entity
@Table(name = "clientes")
@Data
@EqualsAndHashCode(callSuper = true)
@NoArgsConstructor
public class Cliente extends Persona {
    
    @Column(name = "es_concurrente")
    private boolean esConcurrente;
       
    @Column(name = "email", nullable = false, unique = true, length = 30)
    private String eMail;
    
    @Column(name = "t_contacto", nullable = false, unique = true, length = 30)
    private String tContacto;
    
    @OneToMany(mappedBy = "cliente", cascade = CascadeType.ALL)
    private List<Turno> turnos;

}
