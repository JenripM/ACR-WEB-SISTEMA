package cl.javadevs.springsecurityjwt.services;

import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import cl.javadevs.springsecurityjwt.models.Prediccion;
import cl.javadevs.springsecurityjwt.repositories.PrediccionRepository;

@Service
public class PrediccionService {

    @Autowired
    private PrediccionRepository prediccionRepository;

    public Prediccion addPrediccion(Prediccion prediccion) {
        return prediccionRepository.save(prediccion);
    }

    public List<Prediccion> getAllPredicciones() {
        return prediccionRepository.findAll();
    }

    public Optional<Prediccion> getPrediccionById(int id) {
        return prediccionRepository.findById(id);
    }

    public Prediccion updatePrediccion(int id, Prediccion prediccionDetails) {
        Prediccion prediccion = prediccionRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Predicción no encontrada"));
        
        if (prediccionDetails.getIdCliente() != null) {
            prediccion.setIdCliente(prediccionDetails.getIdCliente());
        }
        if (prediccionDetails.getEdad() != null) {
            prediccion.setEdad(prediccionDetails.getEdad());
        }
        if (prediccionDetails.getGenero() != null) {
            prediccion.setGenero(prediccionDetails.getGenero());
        }
        if (prediccionDetails.getTipoCaso() != null) {
            prediccion.setTipoCaso(prediccionDetails.getTipoCaso());
        }
        if (prediccionDetails.getFechaInicio() != null) {
            prediccion.setFechaInicio(prediccionDetails.getFechaInicio());
        }
        if (prediccionDetails.getFechaCierre() != null) {
            prediccion.setFechaCierre(prediccionDetails.getFechaCierre());
        }
        if (prediccionDetails.getUltimaActividad() != null) {
            prediccion.setUltimaActividad(prediccionDetails.getUltimaActividad());
        }
        if (prediccionDetails.getPrediccion() != null) {
            prediccion.setPrediccion(prediccionDetails.getPrediccion());
        }
        
        return prediccionRepository.save(prediccion);
    }

    public void deletePrediccion(int id) {
        prediccionRepository.deleteById(id);
    }

    public List<Prediccion> getPrediccionesByClienteId(int clienteId) {
        return prediccionRepository.findByIdCliente(clienteId);
    }
}
