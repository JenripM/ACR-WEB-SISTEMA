package com.backend_acr.springboot.web.app.caso;

import com.backend_acr.springboot.web.app.cliente.Cliente;
import com.backend_acr.springboot.web.app.cliente.ClienteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CasoService {

    @Autowired
    private CasoRepository casoRepository;

    @Autowired
    private ClienteRepository clienteRepository;

    public Caso addCaso(Caso caso) {
        return casoRepository.save(caso);
    }

    public List<Caso> getAllCasos() {
        return casoRepository.findAll();
    }

    public Optional<Caso> getCasoById(int id) {
        return casoRepository.findById(id);
    }

    public Caso updateCaso(int id, Caso casoDetails) {
        Caso caso = casoRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Caso not found"));
        caso.setTipo(casoDetails.getTipo());
        caso.setEstado(casoDetails.getEstado());
        caso.setFecha_inicio(casoDetails.getFecha_inicio());
        caso.setFecha_cierre(casoDetails.getFecha_cierre());
        caso.setDescripcion(casoDetails.getDescripcion());
        caso.setCliente(casoDetails.getCliente());
        return casoRepository.save(caso);
    }

    public void deleteCaso(int id) {
        casoRepository.deleteById(id);
    }
}
