package cl.javadevs.springsecurityjwt.controllers;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import cl.javadevs.springsecurityjwt.models.Prediccion;
import cl.javadevs.springsecurityjwt.services.PrediccionService;

@RestController
@CrossOrigin
@RequestMapping("/api/v1/prediccion")
public class PrediccionController {

    @Autowired
    private PrediccionService prediccionService;

    @PostMapping(path = "/save")
    public ResponseEntity<Prediccion> savePrediccion(@RequestBody Prediccion prediccion) {
        Prediccion newPrediccion = prediccionService.addPrediccion(prediccion);
        return ResponseEntity.ok(newPrediccion);
    }

    @GetMapping(path = "/all")
    public ResponseEntity<List<Prediccion>> getAllPredicciones() {
        List<Prediccion> predicciones = prediccionService.getAllPredicciones();
        return ResponseEntity.ok(predicciones);
    }

    @GetMapping(path = "/{id}")
    public ResponseEntity<Prediccion> getPrediccionById(@PathVariable int id) {
        Prediccion prediccion = prediccionService.getPrediccionById(id)
                .orElseThrow(() -> new RuntimeException("Predicción not found"));
        return ResponseEntity.ok(prediccion);
    }

    @PutMapping(path = "/update/{id}")
    public ResponseEntity<Prediccion> updatePrediccion(@PathVariable int id, @RequestBody Prediccion prediccionDetails) {
        Prediccion updatedPrediccion = prediccionService.updatePrediccion(id, prediccionDetails);
        return ResponseEntity.ok(updatedPrediccion);
    }

    @DeleteMapping(path = "/delete/{id}")
    public ResponseEntity<Void> deletePrediccion(@PathVariable int id) {
        prediccionService.deletePrediccion(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping(path = "/cliente/{clienteId}")
    public ResponseEntity<List<Prediccion>> getPrediccionesByClienteId(@PathVariable int clienteId) {
        List<Prediccion> predicciones = prediccionService.getPrediccionesByClienteId(clienteId);
        if (predicciones.isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(predicciones);
    }
}
