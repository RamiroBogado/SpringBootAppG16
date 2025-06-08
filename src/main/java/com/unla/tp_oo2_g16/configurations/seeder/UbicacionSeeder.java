package com.unla.tp_oo2_g16.configurations.seeder;

import com.unla.tp_oo2_g16.models.entities.Localidad;
import com.unla.tp_oo2_g16.models.entities.Ubicacion;
import com.unla.tp_oo2_g16.repositories.LocalidadRepository;
import com.unla.tp_oo2_g16.repositories.UbicacionRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

@Component
@Order(2)
public class UbicacionSeeder implements CommandLineRunner {

    private final LocalidadRepository localidadRepository;
    private final UbicacionRepository ubicacionRepository;

    public UbicacionSeeder(LocalidadRepository localidadRepository, UbicacionRepository ubicacionRepository) {
        this.localidadRepository = localidadRepository;
        this.ubicacionRepository = ubicacionRepository;
    }

    @Override
    public void run(String... args) throws Exception {
        if (ubicacionRepository.count() == 0) {
            cargarUbicaciones();
        }
    }

    private void cargarUbicaciones() {
        Localidad lomas = localidadRepository.findById(1).orElseThrow();
        Localidad temperley = localidadRepository.findById(2).orElseThrow();
        Localidad banfield = localidadRepository.findById(3).orElseThrow();

        ubicacionRepository.save(new Ubicacion(null, "Av. Hipólito Yrigoyen 1000", lomas, null));
        ubicacionRepository.save(new Ubicacion(null, "Calle San Martín 200", temperley, null));
        ubicacionRepository.save(new Ubicacion(null, "Av. Alsina 300", banfield, null));
    }
}
