package com.backend_acr.springboot.web.app.trabajador;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class TrabajadorService {

    @Autowired
    private TrabajadorRepository trabajadorRepository;

    public String addTrabajador(TrabajadorDTO trabajadorDTO) {
        Trabajador trabajador = new Trabajador();
        trabajador.setNombres(trabajadorDTO.getNombres());
        trabajador.setApellidos(trabajadorDTO.getApellidos());
        trabajador.setDireccion(trabajadorDTO.getDireccion());
        trabajador.setEmail(trabajadorDTO.getEmail());
        trabajador.setCelular(trabajadorDTO.getCelular());
        trabajador = trabajadorRepository.save(trabajador);
        return String.valueOf(trabajador.getTrabajadorId());
    }

    public List<TrabajadorDTO> getAllTrabajadores() {
        return trabajadorRepository.findAll().stream().map(this::convertToDTO).collect(Collectors.toList());
    }

    public TrabajadorDTO getTrabajadorById(int id) {
        Trabajador trabajador = trabajadorRepository.findById(id).orElseThrow(() -> new RuntimeException("Trabajador no encontrado"));
        return convertToDTO(trabajador);
    }

    public TrabajadorDTO updateTrabajador(int id, TrabajadorDTO trabajadorDTO) {
        Trabajador trabajador = trabajadorRepository.findById(id).orElseThrow(() -> new RuntimeException("Trabajador no encontrado"));
        trabajador.setNombres(trabajadorDTO.getNombres());
        trabajador.setApellidos(trabajadorDTO.getApellidos());
        trabajador.setDireccion(trabajadorDTO.getDireccion());
        trabajador.setEmail(trabajadorDTO.getEmail());
        trabajador.setCelular(trabajadorDTO.getCelular());
        trabajador = trabajadorRepository.save(trabajador);
        return convertToDTO(trabajador);
    }

    public void deleteTrabajador(int id) {
        trabajadorRepository.deleteById(id);
    }

    private TrabajadorDTO convertToDTO(Trabajador trabajador) {
        TrabajadorDTO trabajadorDTO = new TrabajadorDTO();
        trabajadorDTO.setTrabajadorId(trabajador.getTrabajadorId());
        trabajadorDTO.setNombres(trabajador.getNombres());
        trabajadorDTO.setApellidos(trabajador.getApellidos());
        trabajadorDTO.setDireccion(trabajador.getDireccion());
        trabajadorDTO.setEmail(trabajador.getEmail());
        trabajadorDTO.setCelular(trabajador.getCelular());
        return trabajadorDTO;
    }
}

