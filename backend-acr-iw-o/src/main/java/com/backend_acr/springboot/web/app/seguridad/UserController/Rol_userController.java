package com.backend_acr.springboot.web.app.seguridad.UserController;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


import com.backend_acr.springboot.web.app.seguridad.Entity.Rol_user;
import com.backend_acr.springboot.web.app.seguridad.Service.Rol_userService;

import java.util.List;

@RestController
@CrossOrigin
@RequestMapping("/api/v1/rol_user")
public class Rol_userController {

	@Autowired
	private Rol_userService rol_userService;
	
	@PostMapping(path = "/save")
    public ResponseEntity<Rol_user> saveRU(@RequestBody Rol_user rol_user) {
    	Rol_user newru = rol_userService.addRU(rol_user);
        return ResponseEntity.ok(newru);
    }
    
    @GetMapping(path = "/all")
    public ResponseEntity<List<Rol_user>> getAllRUs() {
        List<Rol_user> rol_users = rol_userService.getAllRUs();
        return ResponseEntity.ok(rol_users);
    }
    
    @GetMapping(path = "/{id}")
    public ResponseEntity<Rol_user> getRUById(@PathVariable int id) {
    	Rol_user rol_user = rol_userService.getRUById(id).orElseThrow(() -> new RuntimeException("Rol not found"));
        return ResponseEntity.ok(rol_user);
    }
    
    @PutMapping(path = "/update/{id}")
    public ResponseEntity<Rol_user> updateRU(@PathVariable int id, @RequestBody Rol_user ruDetails) {
    	Rol_user updatedRU = rol_userService.updateRU(id, ruDetails);
        return ResponseEntity.ok(updatedRU);
    }
    
    @DeleteMapping(path = "/delete/{id}")
    public ResponseEntity<Void> deleteRU(@PathVariable int id) {
    	rol_userService.deleteRU(id);
        return ResponseEntity.noContent().build();
    }
}
