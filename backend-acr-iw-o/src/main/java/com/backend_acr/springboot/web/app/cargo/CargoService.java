package com.backend_acr.springboot.web.app.cargo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CargoService {

    @Autowired
    private CargoRepository cargoRepository;

    public Cargo addCargo(Cargo cargo) {
        return cargoRepository.save(cargo);
    }

    public List<Cargo> getAllCargos() {
        return cargoRepository.findAll();
    }

    public Optional<Cargo> getCargoById(int id) {
        return cargoRepository.findById(id);
    }

    public Cargo updateCargo(int id, Cargo cargoDetails) {
        Cargo cargo = cargoRepository.findById(id).orElseThrow(() -> new RuntimeException("Cargo not found"));
        cargo.setNombre(cargoDetails.getNombre());
        return cargoRepository.save(cargo);
    }

    public void deleteCargo(int id) {
        cargoRepository.deleteById(id);
    }
}
