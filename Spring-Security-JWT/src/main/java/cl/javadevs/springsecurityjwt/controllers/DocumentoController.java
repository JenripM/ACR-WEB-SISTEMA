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

import cl.javadevs.springsecurityjwt.models.Documento;
import cl.javadevs.springsecurityjwt.services.DocumentoService;

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
