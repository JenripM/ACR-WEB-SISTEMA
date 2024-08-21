package cl.javadevs.springsecurityjwt.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cl.javadevs.springsecurityjwt.models.Documento;
import cl.javadevs.springsecurityjwt.repositories.DocumentoRepository;


@Service
public class DocumentoService {

    @Autowired
    private DocumentoRepository documentoRepository;


    public Documento addDocumento(Documento documento) {
        return documentoRepository.save(documento);
    }

    public List<Documento> getAllDocumentos() {
        return documentoRepository.findAll();
    }

    public Optional<Documento> getDocumentoById(Integer id) {
        return documentoRepository.findById(id);
    }

    public Documento updateDocumento(Integer id, Documento documentoDetails) {
        Documento documento = documentoRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Documento not found"));
        documento.setTipo(documentoDetails.getTipo());
        documento.setNombreArchivo(documentoDetails.getNombreArchivo());
        documento.setCaso(documentoDetails.getCaso());
        return documentoRepository.save(documento);
    }

    public void deleteDocumento(Integer id) {
        documentoRepository.deleteById(id);
    }
}
