package com.backend_acr.springboot.web.app.seguridad.Service.Impl;

import com.backend_acr.springboot.web.app.seguridad.Dto.EmployeeDTO;
import com.backend_acr.springboot.web.app.seguridad.Dto.LoginDTO;
import com.backend_acr.springboot.web.app.seguridad.Entity.Employee;
import com.backend_acr.springboot.web.app.seguridad.Repo.EmployeeRepo;
import com.backend_acr.springboot.web.app.seguridad.Service.EmployeeService;
import com.backend_acr.springboot.web.app.seguridad.response.LoginResponse;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;


@Service
public class EmployeeIMPL implements EmployeeService {
    @Autowired
    private EmployeeRepo employeeRepo;
    @Autowired
    private PasswordEncoder passwordEncoder;
    @Override
    public String addEmployee(EmployeeDTO employeeDTO) {
        Employee employee = new Employee(
                employeeDTO.getEmployeeid(),
                employeeDTO.getEmployeename(),
                employeeDTO.getEmail(),
               this.passwordEncoder.encode(employeeDTO.getPassword())
        );
        employeeRepo.save(employee);
        return employee.getEmployeename();
    }
   
    
    EmployeeDTO employeeDTO;
    @Override
    public LoginResponse  loginEmployee(LoginDTO loginDTO) {
        String msg = "";
        Employee employee1 = employeeRepo.findByEmail(loginDTO.getEmail());
        if (employee1 != null) {
            String password = loginDTO.getPassword();
            String encodedPassword = employee1.getPassword();
            Boolean isPwdRight = passwordEncoder.matches(password, encodedPassword);
            if (isPwdRight) {
                Optional<Employee> employee = employeeRepo.findOneByEmailAndPassword(loginDTO.getEmail(), encodedPassword);
                if (employee.isPresent()) {
                    return new LoginResponse("Login Success", true);
                } else {
                    return new LoginResponse("Login Failed", false);
                }
            } else {
                return new LoginResponse("password Not Match", false);
            }
        }else {
            return new LoginResponse("Email not exits", false);
        }
    }
    
    
    @Override
    public List<EmployeeDTO> getAllEmployees() {
        return employeeRepo.findAll().stream().map(employee -> new EmployeeDTO(
                employee.getEmployeeid(),
                employee.getEmployeename(),
                employee.getEmail(),
                employee.getPassword()
        )).collect(Collectors.toList());
    }
}