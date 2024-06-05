package com.backend_acr.springboot.web.app.seguridad.Service.Impl;

import com.backend_acr.springboot.web.app.seguridad.Dto.UserDTO;
import com.backend_acr.springboot.web.app.seguridad.Dto.LoginDTO;
import com.backend_acr.springboot.web.app.seguridad.Entity.User;
import com.backend_acr.springboot.web.app.seguridad.Repo.UserRepo;
import com.backend_acr.springboot.web.app.seguridad.Service.UserService;
import com.backend_acr.springboot.web.app.seguridad.response.LoginResponse;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;


@Service
public class UserIMPL implements UserService {
    @Autowired
    private UserRepo userRepo;
    @Autowired
    private PasswordEncoder passwordEncoder;
    @Override
    public String addUser(UserDTO userDTO) {
        User user = new User(
                userDTO.getUserId(),
                userDTO.getUserName(),
                userDTO.getEmail(),
               this.passwordEncoder.encode(userDTO.getPassword())
        );
        userRepo.save(user);
        return user.getUserName();
    }
   
    
    UserDTO userDTO;
    @Override
    public LoginResponse  loginUser(LoginDTO loginDTO) {
        String msg = "";
        User user1 = userRepo.findByEmail(loginDTO.getEmail());
        if (user1 != null) {
            String password = loginDTO.getPassword();
            String encodedPassword = user1.getPassword();
            Boolean isPwdRight = passwordEncoder.matches(password, encodedPassword);
            if (isPwdRight) {
                Optional<User> user = userRepo.findOneByEmailAndPassword(loginDTO.getEmail(), encodedPassword);
                if (user.isPresent()) {
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
    public List<UserDTO> getAllUsers() {
        return userRepo.findAll().stream().map(user -> new UserDTO(
                user.getUserId(),
                user.getUserName(),
                user.getEmail(),
                user.getPassword()
        )).collect(Collectors.toList());
    }



}