package com.backend_acr.springboot.web.app.cargo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin
@RequestMapping("/api/v1/cargo")
public class CargoController {

    @Autowired
    private CargoService cargoService;

    @PostMapping(path = "/save")
    public ResponseEntity<Cargo> saveCargo(@RequestBody Cargo cargo) {
        Cargo newCargo = cargoService.addCargo(cargo);
        return ResponseEntity.ok(newCargo);
    }

    @GetMapping(path = "/all")
    public ResponseEntity<List<Cargo>> getAllCargos() {
        List<Cargo> cargos = cargoService.getAllCargos();
        return ResponseEntity.ok(cargos);
    }

    @GetMapping(path = "/{id}")
    public ResponseEntity<Cargo> getCargoById(@PathVariable int id) {
        Cargo cargo = cargoService.getCargoById(id).orElseThrow(() -> new RuntimeException("Cargo not found"));
        return ResponseEntity.ok(cargo);
    }

    @PutMapping(path = "/update/{id}")
    public ResponseEntity<Cargo> updateCargo(@PathVariable int id, @RequestBody Cargo cargoDetails) {
        Cargo updatedCargo = cargoService.updateCargo(id, cargoDetails);
        return ResponseEntity.ok(updatedCargo);
    }

    @DeleteMapping(path = "/delete/{id}")
    public ResponseEntity<Void> deleteCargo(@PathVariable int id) {
        cargoService.deleteCargo(id);
        return ResponseEntity.noContent().build();
    }
}
