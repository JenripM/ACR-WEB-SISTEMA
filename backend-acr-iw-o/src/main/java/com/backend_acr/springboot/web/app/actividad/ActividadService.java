package com.backend_acr.springboot.web.app.actividad;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.backend_acr.springboot.web.app.caso.CasoRepository;
import com.backend_acr.springboot.web.app.trabajador.TrabajadorRepository;

@Service
public class ActividadService {
    @Autowired
    private ActividadRepository actividadRepository;

    @Autowired
    private CasoRepository casoRepository;

    @Autowired
    private TrabajadorRepository trabajadorRepository;

    public Actividad addActividad(Actividad actividad) {
        return actividadRepository.save(actividad);
    }

    public List<Actividad> getAllActividades() {
        return actividadRepository.findAll();
    }

    public Optional<Actividad> getActividadById(int id) {
        return actividadRepository.findById(id);
    }

    public Actividad updateActividad(int id, Actividad actividadDetails) {
        Actividad actividad = actividadRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Actividad not found"));
        actividad.setNombre(actividadDetails.getNombre());
        actividad.setPrioridad(actividadDetails.getPrioridad());
        actividad.setEstado(actividadDetails.getEstado());
        actividad.setFecha_inicio(actividadDetails.getFecha_inicio());
        actividad.setFecha_cierre(actividadDetails.getFecha_cierre());
        actividad.setDescripcion(actividadDetails.getDescripcion());
        actividad.setCaso(actividadDetails.getCaso());
        actividad.setTrabajador(actividadDetails.getTrabajador());
        return actividadRepository.save(actividad);
    }

    public void deleteActividad(int id) {
        actividadRepository.deleteById(id);
    }
}
