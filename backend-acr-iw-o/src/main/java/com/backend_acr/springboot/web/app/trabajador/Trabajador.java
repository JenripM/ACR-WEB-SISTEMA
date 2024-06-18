package com.backend_acr.springboot.web.app.trabajador;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name="trabajadores")
public class Trabajador {
    @Id
    @Column(length = 45)
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int trabajadorId;
    @Column
    private String nombres;
    private String apellidos;
    private String direccion;
    private String email;
    private String celular;
    
    public Trabajador(){
        
    }
	public Trabajador(int trabajadorId, String nombres, String apellidos, String direccion, String email,
			String celular) {
		super();
		this.trabajadorId = trabajadorId;
		this.nombres = nombres;
		this.apellidos = apellidos;
		this.direccion = direccion;
		this.email = email;
		this.celular = celular;
	}

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

	
    
}
