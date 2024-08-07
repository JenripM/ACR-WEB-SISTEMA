package cl.javadevs.springsecurityjwt.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import cl.javadevs.springsecurityjwt.models.Caso;
import cl.javadevs.springsecurityjwt.services.CasoService;

import java.util.List;

@RestController
@CrossOrigin
@RequestMapping("/api/v1/caso")
public class CasoController {

    @Autowired
    private CasoService casoService;

    @PostMapping(path = "/save")
    public ResponseEntity<Caso> saveCaso(@RequestBody Caso caso) {
        Caso newCaso = casoService.addCaso(caso);
        return ResponseEntity.ok(newCaso);
    }

    @GetMapping(path = "/all")
    public ResponseEntity<List<Caso>> getAllCasos() {
        List<Caso> casos = casoService.getAllCasos();
        return ResponseEntity.ok(casos);
    }

    @GetMapping(path = "/{id}")
    public ResponseEntity<Caso> getCasoById(@PathVariable int id) {
        Caso caso = casoService.getCasoById(id)
                .orElseThrow(() -> new RuntimeException("Caso not found"));
        return ResponseEntity.ok(caso);
    }

    @PutMapping(path = "/update/{id}")
    public ResponseEntity<Caso> updateCaso(@PathVariable int id, @RequestBody Caso casoDetails) {
        Caso updatedCaso = casoService.updateCaso(id, casoDetails);
        return ResponseEntity.ok(updatedCaso);
    }

    @DeleteMapping(path = "/delete/{id}")
    public ResponseEntity<Void> deleteCaso(@PathVariable int id) {
        casoService.deleteCaso(id);
        return ResponseEntity.noContent().build();
    }
}
