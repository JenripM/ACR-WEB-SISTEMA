package com.backend_acr.springboot.web.app.seguridad.Service;

import java.util.List;

import com.backend_acr.springboot.web.app.seguridad.Dto.UserDTO;
import com.backend_acr.springboot.web.app.seguridad.Dto.LoginDTO;
import com.backend_acr.springboot.web.app.seguridad.response.LoginResponse;
public interface UserService {
    String addUser(UserDTO userDTO);
    LoginResponse loginUser(LoginDTO loginDTO);
    List<UserDTO> getAllUsers();  // Añadir este método

}