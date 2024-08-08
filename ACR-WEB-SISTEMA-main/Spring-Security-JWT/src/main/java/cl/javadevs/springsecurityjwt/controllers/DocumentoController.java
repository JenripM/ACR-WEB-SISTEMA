package cl.javadevs.springsecurityjwt.controllers;

import java.io.IOException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.FileSystemResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import cl.javadevs.springsecurityjwt.models.Documento;
import cl.javadevs.springsecurityjwt.services.DocumentoService;
import jakarta.annotation.Resource;

@RestController
@CrossOrigin(origins = "http://localhost:4200") // Especifica el origen frontend
@RequestMapping("/api/v1/documento")
public class DocumentoController {

    @Autowired
    private DocumentoService documentoService;

    /**
     * Subir un nuevo documento.
     * @param casoId ID del caso asociado.
     * @param archivo El archivo PDF a subir.
     * @param tipo Tipo del documento.
     * @return ResponseEntity con el estado y el documento guardado.
     */
    @PostMapping("/upload")
    public ResponseEntity<Documento> uploadDocumento(
            @RequestParam("casoId") Integer casoId,
            @RequestParam("archivo") MultipartFile archivo,
            @RequestParam("tipo") String tipo) {
        try {
            Documento documento = documentoService.uploadDocumento(casoId, archivo, tipo);
            return new ResponseEntity<>(documento, HttpStatus.CREATED);
        } catch (IOException e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    @GetMapping(path = "/all")
    public ResponseEntity<List<Documento>> getAllDocumentos() {
        List<Documento> Documentos = documentoService.getAllDocumentos();
        return ResponseEntity.ok(Documentos);
    }
    /**
     * Actualiza un documento existente.
     * @param id ID del documento a actualizar.
     * @param archivo El archivo PDF para reemplazar.
     * @param tipo Tipo del documento.
     * @return ResponseEntity con el estado y el documento actualizado.
     */
    @PutMapping("/{id}")
    public ResponseEntity<Documento> updateDocumento(
            @PathVariable("id") Integer id,
            @RequestParam("archivo") MultipartFile archivo,
            @RequestParam("tipo") String tipo) {
        try {
            Documento documento = documentoService.updateDocumento(id, archivo, tipo);
            return new ResponseEntity<>(documento, HttpStatus.OK);
        } catch (IOException e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    /**
     * Elimina un documento por ID.
     * @param id ID del documento a eliminar.
     * @return ResponseEntity con el estado.
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<HttpStatus> deleteDocumento(@PathVariable("id") Integer id) {
        try {
            documentoService.deleteDocumento(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    /**
     * Descarga un archivo basado en el ID del documento.
     * @param id ID del documento.
     * @return ResponseEntity con el archivo y encabezados adecuados.
     */
    @GetMapping("/download/{id}")
    public ResponseEntity<Resource> downloadArchivo(@PathVariable("id") Integer id) {
        Resource recurso = documentoService.cargarArchivo(id);
        if (recurso != null) {
            String filename = "unknown"; // Default filename
            
            if (recurso instanceof FileSystemResource fileSystemResource) {
                filename = fileSystemResource.getFilename();
            }
            
            return ResponseEntity.ok()
                    .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + filename + "\"")
                    .body(recurso);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}
