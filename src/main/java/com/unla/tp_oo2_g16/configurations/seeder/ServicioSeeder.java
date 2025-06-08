package com.unla.tp_oo2_g16.configurations.seeder;

import com.unla.tp_oo2_g16.models.entities.Servicio;
import com.unla.tp_oo2_g16.repositories.ServicioRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

@Component
@Order(3)
public class ServicioSeeder implements CommandLineRunner {

    private final ServicioRepository servicioRepository;

    public ServicioSeeder(ServicioRepository servicioRepository) {
        this.servicioRepository = servicioRepository;
    }

    @Override
    public void run(String... args) throws Exception {
        if (servicioRepository.count() == 0) {
            cargarServicios();
        }
    }

    private void cargarServicios() {
        servicioRepository.save(new Servicio("Consulta General", "Consulta médica general", 30));
        servicioRepository.save(new Servicio("Odontología", "Tratamiento odontológico", 45));
        servicioRepository.save(new Servicio("Psicología", "Sesión psicológica individual", 60));
        System.out.println("Servicios cargados!");
    }
}
