package com.backend_acr.springboot.web.app.trabajador;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin
@RequestMapping("/api/v1/trabajador")
public class TrabajadorController {

    @Autowired
    private TrabajadorService trabajadorService;

    @PostMapping(path = "/save")
    public ResponseEntity<Trabajador> saveTrabajador(@RequestBody Trabajador trabajador) {
        Trabajador newTrabajador = trabajadorService.addTrabajador(trabajador);
        return ResponseEntity.ok(newTrabajador);
    }

    @GetMapping(path = "/all")
    public ResponseEntity<List<Trabajador>> getAllTrabajadores() {
        List<Trabajador> trabajadores = trabajadorService.getAllTrabajadores();
        return ResponseEntity.ok(trabajadores);
    }

    @GetMapping(path = "/{id}")
    public ResponseEntity<Trabajador> getTrabajadorById(@PathVariable int id) {
        Trabajador trabajador = trabajadorService.getTrabajadorById(id)
                .orElseThrow(() -> new RuntimeException("Trabajador not found"));
        return ResponseEntity.ok(trabajador);
    }

    @PutMapping(path = "/update/{id}")
    public ResponseEntity<Trabajador> updateTrabajador(@PathVariable int id, @RequestBody Trabajador trabajadorDetails) {
        Trabajador updatedTrabajador = trabajadorService.updateTrabajador(id, trabajadorDetails);
        return ResponseEntity.ok(updatedTrabajador);
    }

    @DeleteMapping(path = "/delete/{id}")
    public ResponseEntity<Void> deleteTrabajador(@PathVariable int id) {
        trabajadorService.deleteTrabajador(id);
        return ResponseEntity.noContent().build();
    }
}
