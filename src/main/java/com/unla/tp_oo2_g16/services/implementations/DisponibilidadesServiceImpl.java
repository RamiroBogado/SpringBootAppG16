package com.unla.tp_oo2_g16.services.implementations;

import java.time.LocalTime;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.unla.tp_oo2_g16.models.entities.Disponibilidad;
import com.unla.tp_oo2_g16.repositories.DisponibilidadRepository;
import com.unla.tp_oo2_g16.services.interfaces.DisponibilidadesServiceInterface;

@Service
public class DisponibilidadesServiceImpl implements DisponibilidadesServiceInterface {

    @Autowired
    private DisponibilidadRepository disponibilidadRepository;

    @Override
    public List<Disponibilidad> findAll() {
        return disponibilidadRepository.findAll();
    }

    @Override
    public Disponibilidad findById(Integer id) {
        return disponibilidadRepository.findById(id).orElse(null);
    }

    @Override
    public Disponibilidad save(Disponibilidad disponibilidad) {
        return disponibilidadRepository.save(disponibilidad);
    }

    @Override
    public void deleteById(Integer id) {
        disponibilidadRepository.deleteById(id);
    }
    
    @Override
    public List<LocalTime> findAllhorariosDisponibles() {
        List<Disponibilidad> disponibilidades = disponibilidadRepository.findAll();

        Set<LocalTime> horariosSet = new HashSet<>();

        for (Disponibilidad d : disponibilidades) {
            LocalTime start = d.getHorarioInicio();
            LocalTime end = d.getHorarioFin();

            // Generar horarios de 1 hora entre start y end (inclusive start, exclusivo end)
            LocalTime time = start;
            while (time.isBefore(end)) {
                horariosSet.add(time);
                time = time.plusHours(1);
            }
        }

        // Convertir a lista y ordenar
        List<LocalTime> horarios = new ArrayList<>(horariosSet);
        horarios.sort(LocalTime::compareTo);

        return horarios;
    }

}
