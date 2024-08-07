package cl.javadevs.springsecurityjwt.services;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.FileSystemResource;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import cl.javadevs.springsecurityjwt.models.Caso;
import cl.javadevs.springsecurityjwt.models.Documento;
import cl.javadevs.springsecurityjwt.repositories.DocumentoRepository;
import jakarta.annotation.Resource;

@Service
public class DocumentoService {

    @Autowired
    private DocumentoRepository documentoRepository;

    @Autowired
    private CasoService casoService; // Añadido para buscar el caso asociado

    private final String rutaDocumentos = "src/main/resources/documentos"; // Ruta dentro del proyecto

    /**
     * Guarda un nuevo documento en la base de datos y almacena el archivo en disco.
     * @param casoId ID del caso asociado.
     * @param archivo El archivo PDF a guardar.
     * @param tipo Tipo del documento.
     * @return El documento guardado.
     * @throws IOException Si ocurre un error al guardar el archivo.
     */
    public Documento uploadDocumento(Integer casoId, MultipartFile archivo, String tipo) throws IOException {
        Documento documento = new Documento();
        documento.setTipo(tipo);  // Establece el tipo del documento

        // Buscar el caso por ID y asociarlo al documento
        Caso caso = casoService.getCasoById(casoId)
                .orElseThrow(() -> new RuntimeException("Caso not found"));
        documento.setCaso(caso);

        // Guardar archivo en la carpeta del proyecto
        if (archivo != null && !archivo.isEmpty()) {
            String rutaArchivo = guardarArchivoEnCarpeta(archivo);
            documento.setRutaArchivo(rutaArchivo);
            documento.setDatos(archivo.getBytes());  // Guarda el contenido del archivo
        }

        return documentoRepository.save(documento);
    }

    /**
     * Actualiza un documento existente, reemplazando el archivo y los campos especificados.
     * @param id ID del documento a actualizar.
     * @param archivo El archivo PDF a reemplazar.
     * @param tipo Tipo del documento.
     * @return El documento actualizado.
     * @throws IOException Si ocurre un error al guardar el archivo.
     */
    public Documento updateDocumento(Integer id, MultipartFile archivo, String tipo) throws IOException {
        Documento documento = documentoRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Documento not found"));

        // Actualizar archivo en la carpeta del proyecto
        if (archivo != null && !archivo.isEmpty()) {
            String rutaArchivo = guardarArchivoEnCarpeta(archivo);
            documento.setRutaArchivo(rutaArchivo);
            documento.setDatos(archivo.getBytes());  // Actualiza el contenido del archivo
        }

        documento.setTipo(tipo);  // Actualiza el tipo de documento
        return documentoRepository.save(documento);
    }

    /**
     * Elimina un documento de la base de datos por ID.
     * @param id ID del documento a eliminar.
     */
    public void deleteDocumento(Integer id) {
        documentoRepository.deleteById(id);
    }

    /**
     * Guarda el archivo en la carpeta especificada y retorna la ruta.
     * @param archivo El archivo a guardar.
     * @return La ruta del archivo guardado.
     * @throws IOException Si ocurre un error al guardar el archivo.
     */
    private String guardarArchivoEnCarpeta(MultipartFile archivo) throws IOException {
        File carpeta = new File(rutaDocumentos);
        if (!carpeta.exists()) {
            carpeta.mkdirs();  // Crear la carpeta si no existe
        }

        File archivoEnCarpeta = new File(carpeta, archivo.getOriginalFilename());
        try (FileOutputStream fos = new FileOutputStream(archivoEnCarpeta)) {
            fos.write(archivo.getBytes());
        }
        return archivoEnCarpeta.getAbsolutePath();  // Retorna la ruta del archivo
    }

    /**
     * Carga un archivo basado en el ID del documento.
     * @param id ID del documento.
     * @return Un recurso de archivo si se encuentra, o null si no existe.
     */
    public Resource cargarArchivo(Integer id) {
        Optional<Documento> documentoOpt = documentoRepository.findById(id);
        if (documentoOpt.isPresent()) {
            Documento documento = documentoOpt.get();
            File archivo = new File(documento.getRutaArchivo());
            if (archivo.exists()) {
                return (Resource) new FileSystemResource(archivo);
            }
        }
        return null;
    }
}
