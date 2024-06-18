package com.backend_acr.springboot.web.app.trabajador;

import com.backend_acr.springboot.web.app.cargo.Cargo;
import com.backend_acr.springboot.web.app.cargo.CargoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TrabajadorService {

    @Autowired
    private TrabajadorRepository trabajadorRepository;

    @Autowired
    private CargoRepository cargoRepository;

    public Trabajador addTrabajador(Trabajador trabajador) {
        return trabajadorRepository.save(trabajador);
    }

    public List<Trabajador> getAllTrabajadores() {
        return trabajadorRepository.findAll();
    }

    public Optional<Trabajador> getTrabajadorById(int id) {
        return trabajadorRepository.findById(id);
    }

    public Trabajador updateTrabajador(int id, Trabajador trabajadorDetails) {
        Trabajador trabajador = trabajadorRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Trabajador not found"));
        trabajador.setNombres(trabajadorDetails.getNombres());
        trabajador.setApellidos(trabajadorDetails.getApellidos());
        trabajador.setDireccion(trabajadorDetails.getDireccion());
        trabajador.setEmail(trabajadorDetails.getEmail());
        trabajador.setCelular(trabajadorDetails.getCelular());
        trabajador.setCargo(trabajadorDetails.getCargo());
        return trabajadorRepository.save(trabajador);
    }

    public void deleteTrabajador(int id) {
        trabajadorRepository.deleteById(id);
    }
}
