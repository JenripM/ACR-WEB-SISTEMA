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
    public ResponseEntity<String> saveTrabajador(@RequestBody TrabajadorDTO trabajadorDTO) {
        String id = trabajadorService.addTrabajador(trabajadorDTO);
        return ResponseEntity.ok(id);
    }

    @GetMapping(path = "/all")
    public ResponseEntity<List<TrabajadorDTO>> getAllTrabajadores() {
        List<TrabajadorDTO> trabajadores = trabajadorService.getAllTrabajadores();
        return ResponseEntity.ok(trabajadores);
    }

    @GetMapping(path = "/{id}")
    public ResponseEntity<TrabajadorDTO> getTrabajadorById(@PathVariable int id) {
        TrabajadorDTO trabajador = trabajadorService.getTrabajadorById(id);
        return ResponseEntity.ok(trabajador);
    }

    @PutMapping(path = "/update/{id}")
    public ResponseEntity<TrabajadorDTO> updateTrabajador(@PathVariable int id, @RequestBody TrabajadorDTO trabajadorDTO) {
        TrabajadorDTO updatedTrabajador = trabajadorService.updateTrabajador(id, trabajadorDTO);
        return ResponseEntity.ok(updatedTrabajador);
    }

    @DeleteMapping(path = "/delete/{id}")
    public ResponseEntity<Void> deleteTrabajador(@PathVariable int id) {
        trabajadorService.deleteTrabajador(id);
        return ResponseEntity.noContent().build();
    }
}
