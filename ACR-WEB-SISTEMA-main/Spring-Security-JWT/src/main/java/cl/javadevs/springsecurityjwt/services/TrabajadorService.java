package cl.javadevs.springsecurityjwt.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cl.javadevs.springsecurityjwt.models.Trabajador;
import cl.javadevs.springsecurityjwt.repositories.TrabajadorRepository;

@Service
public class TrabajadorService {

    @Autowired
    private TrabajadorRepository trabajadorRepository;

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
