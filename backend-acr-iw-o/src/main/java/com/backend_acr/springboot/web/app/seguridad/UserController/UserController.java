package com.backend_acr.springboot.web.app.seguridad.UserController;

import com.backend_acr.springboot.web.app.seguridad.Dto.UserDTO;
import com.backend_acr.springboot.web.app.seguridad.Dto.LoginDTO;
import com.backend_acr.springboot.web.app.seguridad.Service.UserService;
import com.backend_acr.springboot.web.app.seguridad.response.LoginResponse;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
@RestController
@CrossOrigin
@RequestMapping("/api/v1/user")
public class UserController {
    @Autowired
    private UserService userService;
    @PostMapping(path = "/save")
    public String saveUser(@RequestBody UserDTO userDTO)
    {
        String id = userService.addUser(userDTO);
        return id;
    }

    @PostMapping(path = "/login")
    public ResponseEntity<?> loginUser(@RequestBody LoginDTO loginDTO)
    {
        LoginResponse loginResponse = userService.loginUser(loginDTO);
        return ResponseEntity.ok(loginResponse);
    }
    
    @GetMapping(path = "/all")
    public ResponseEntity<List<UserDTO>> getAllUsers() {
        List<UserDTO> users = userService.getAllUsers();
        return ResponseEntity.ok(users);
    }
}