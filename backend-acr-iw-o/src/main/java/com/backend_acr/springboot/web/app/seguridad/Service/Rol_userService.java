package com.backend_acr.springboot.web.app.seguridad.Service;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import com.backend_acr.springboot.web.app.seguridad.Entity.Rol_user;
import com.backend_acr.springboot.web.app.seguridad.Repo.Rol_userRepository;

import java.util.List;
import java.util.Optional;

@Service
public class Rol_userService {

	@Autowired
	private Rol_userRepository rol_userRepository;
	
	public Rol_user addRU(Rol_user rol_user) {
        return rol_userRepository.save(rol_user);
    }

    public List<Rol_user> getAllRUs() {
        return rol_userRepository.findAll();
    }
	
	public Optional<Rol_user> getRUById(int id){
		return rol_userRepository.findById(id);
	}
	
	public Rol_user updateRU(int id, Rol_user ruDetails) {
		Rol_user rol_user = rol_userRepository.findById(id).orElseThrow(() -> new RuntimeException("Rol not found"));
		rol_user.setDescripcion(ruDetails.getDescripcion());
		rol_user.setClientes(ruDetails.getClientes());
		rol_user.setTrabajadores(ruDetails.getTrabajadores());
		rol_user.setCasos(ruDetails.getCasos());
		rol_user.setTareas(ruDetails.getTareas());
		rol_user.setDocum_corres(ruDetails.getDocum_corres());
		rol_user.setAccesos(ruDetails.getAccesos());
        return rol_userRepository.save(rol_user);
    }
	
	public void deleteRU(int id) {
		rol_userRepository.deleteById(id);
    }
}
