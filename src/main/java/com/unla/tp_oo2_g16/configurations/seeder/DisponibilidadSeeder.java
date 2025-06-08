package com.unla.tp_oo2_g16.configurations.seeder;

import com.unla.tp_oo2_g16.models.entities.Disponibilidad;
import com.unla.tp_oo2_g16.models.entities.Servicio;
import com.unla.tp_oo2_g16.repositories.DisponibilidadRepository;
import com.unla.tp_oo2_g16.repositories.ServicioRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import java.time.LocalTime;
import java.util.List;

@Component
@Order(4)
public class DisponibilidadSeeder implements CommandLineRunner {

    private final ServicioRepository servicioRepository;
    private final DisponibilidadRepository disponibilidadRepository;

    public DisponibilidadSeeder(ServicioRepository servicioRepository, DisponibilidadRepository disponibilidadRepository) {
        this.servicioRepository = servicioRepository;
        this.disponibilidadRepository = disponibilidadRepository;
    }

    @Override
    public void run(String... args) throws Exception {
        if (disponibilidadRepository.count() == 0) {
            cargarDisponibilidades();
        }
    }

    private void cargarDisponibilidades() {
        List<Servicio> servicios = servicioRepository.findAll();

        if (servicios.isEmpty()) {
            System.out.println("Faltan servicios para asignar disponibilidades");
            return;
        }

        List<String> dias = List.of("LUNES", "MARTES", "MIERCOLES", "JUEVES", "VIERNES");

        for (Servicio servicio : servicios) {
            int duracion = servicio.getDuracion(); // en minutos
            for (String dia : dias) {
                LocalTime horaInicio = LocalTime.of(9, 0);
                LocalTime horaFin = LocalTime.of(17, 0);

                while (horaInicio.plusMinutes(duracion).isBefore(horaFin.plusSeconds(1))) {
                    LocalTime bloqueFin = horaInicio.plusMinutes(duracion);
                    Disponibilidad disponibilidad = new Disponibilidad(dia, horaInicio, bloqueFin, servicio);
                    disponibilidadRepository.save(disponibilidad);
                    horaInicio = bloqueFin;
                }
            }
        }

        System.out.println("Disponibilidades generadas automáticamente por duración del servicio");
    }
}

