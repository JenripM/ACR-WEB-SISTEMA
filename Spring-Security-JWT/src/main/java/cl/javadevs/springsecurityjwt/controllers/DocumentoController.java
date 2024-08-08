package cl.javadevs.springsecurityjwt.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import cl.javadevs.springsecurityjwt.models.Documento;
import cl.javadevs.springsecurityjwt.services.DocumentoService;

import java.util.List;

@RestController
@CrossOrigin
@RequestMapping("/api/v1/documento")
public class DocumentoController {

    @Autowired
    private DocumentoService documentoService;

    @PostMapping(path = "/save")
    public ResponseEntity<Documento> saveDocumento(@RequestBody Documento documento) {
        Documento newDocumento = documentoService.addDocumento(documento);
        return ResponseEntity.ok(newDocumento);
    }

    @GetMapping(path = "/all")
    public ResponseEntity<List<Documento>> getAllDocumentos() {
        List<Documento> documentos = documentoService.getAllDocumentos();
        return ResponseEntity.ok(documentos);
    }

    @GetMapping(path = "/{id}")
    public ResponseEntity<Documento> getDocumentoById(@PathVariable Integer id) {
        Documento documento = documentoService.getDocumentoById(id)
                .orElseThrow(() -> new RuntimeException("Documento not found"));
        return ResponseEntity.ok(documento);
    }

    @PutMapping(path = "/update/{id}")
    public ResponseEntity<Documento> updateDocumento(@PathVariable Integer id, @RequestBody Documento documentoDetails) {
        Documento updatedDocumento = documentoService.updateDocumento(id, documentoDetails);
        return ResponseEntity.ok(updatedDocumento);
    }

    @DeleteMapping(path = "/delete/{id}")
    public ResponseEntity<Void> deleteDocumento(@PathVariable Integer id) {
        documentoService.deleteDocumento(id);
        return ResponseEntity.noContent().build();
    }
}
