package cl.javadevs.springsecurityjwt.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import cl.javadevs.springsecurityjwt.models.Actividad;
import cl.javadevs.springsecurityjwt.services.ActividadService;

@RestController
@CrossOrigin
@RequestMapping("/api/v1/actividad")
public class ActividadController {

    @Autowired
    private ActividadService actividadService;

    @PostMapping(path = "/save")
    public ResponseEntity<Actividad> saveActividad(@RequestBody Actividad actividad) {
        Actividad newActividad = actividadService.addActividad(actividad);
        return ResponseEntity.ok(newActividad);
    }

    @GetMapping(path = "/all")
    public ResponseEntity<List<Actividad>> getAllActividad() {
        List<Actividad> actividad = actividadService.getAllActividades();
        return ResponseEntity.ok(actividad);
    }

    @GetMapping(path = "/{id}")
    public ResponseEntity<Actividad> getActividadById(@PathVariable int id) {
        Actividad actividad = actividadService.getActividadById(id)
                .orElseThrow(() -> new RuntimeException("Caso not found"));
        return ResponseEntity.ok(actividad);
    }

    @PutMapping(path = "/update/{id}")
    public ResponseEntity<Actividad> updateActividad(@PathVariable int id, @RequestBody Actividad actividadDetails) {
        Actividad updatedActividad = actividadService.updateActividad(id, actividadDetails);
        return ResponseEntity.ok(updatedActividad);
    }

    @DeleteMapping(path = "/delete/{id}")
    public ResponseEntity<Void> deleteActividad(@PathVariable int id) {
        actividadService.deleteActividad(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping(path = "/caso/{casoId}")
    public ResponseEntity<List<Actividad>> getActividadesByCasoId(@PathVariable int casoId) {
        List<Actividad> actividades = actividadService.getActividadesByCasoId(casoId);
        if (actividades.isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(actividades);
    }

}
