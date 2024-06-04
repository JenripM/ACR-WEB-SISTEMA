package com.backend_acr.springboot.web.app.seguridad.Service;

import java.util.List;

import com.backend_acr.springboot.web.app.seguridad.Dto.EmployeeDTO;
import com.backend_acr.springboot.web.app.seguridad.Dto.LoginDTO;
import com.backend_acr.springboot.web.app.seguridad.response.LoginResponse;
public interface EmployeeService {
    String addEmployee(EmployeeDTO employeeDTO);
    LoginResponse loginEmployee(LoginDTO loginDTO);
    List<EmployeeDTO> getAllEmployees();  // Añadir este método

}