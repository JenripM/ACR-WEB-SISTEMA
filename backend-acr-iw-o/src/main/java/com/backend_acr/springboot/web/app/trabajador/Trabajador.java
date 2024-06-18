package com.backend_acr.springboot.web.app.trabajador;

import com.backend_acr.springboot.web.app.cargo.Cargo;
import jakarta.persistence.*;

@Entity
@Table(name="trabajadores")
public class Trabajador {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(length = 45)
    private int trabajadorId;

    @Column(nullable = false)
    private String nombres;

    @Column(nullable = false)
    private String apellidos;

    @Column(nullable = false)
    private String direccion;

    @Column(nullable = false, unique = true)
    private String email;

    @Column(nullable = false)
    private String celular;

    @ManyToOne
    @JoinColumn(name = "cargo_id", nullable = false)
    private Cargo cargo;

    public Trabajador() {
    }

    public Trabajador(int trabajadorId, String nombres, String apellidos, String direccion, String email, String celular, Cargo cargo) {
        this.trabajadorId = trabajadorId;
        this.nombres = nombres;
        this.apellidos = apellidos;
        this.direccion = direccion;
        this.email = email;
        this.celular = celular;
        this.cargo = cargo;
    }

    // Getters and Setters
    public int getTrabajadorId() {
        return trabajadorId;
    }

    public void setTrabajadorId(int trabajadorId) {
        this.trabajadorId = trabajadorId;
    }

    public String getNombres() {
        return nombres;
    }

    public void setNombres(String nombres) {
        this.nombres = nombres;
    }

    public String getApellidos() {
        return apellidos;
    }

    public void setApellidos(String apellidos) {
        this.apellidos = apellidos;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getCelular() {
        return celular;
    }

    public void setCelular(String celular) {
        this.celular = celular;
    }

    public Cargo getCargo() {
        return cargo;
    }

    public void setCargo(Cargo cargo) {
        this.cargo = cargo;
    }
}
