package cl.javadevs.springsecurityjwt.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cl.javadevs.springsecurityjwt.models.Actividad;
import cl.javadevs.springsecurityjwt.repositories.ActividadRepository;

@Service
public class ActividadService {
    @Autowired
    private ActividadRepository actividadRepository;

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

    public List<Actividad> getActividadesByCasoId(int casoId) {
        return actividadRepository.findByCasoId(casoId);
    }
}
