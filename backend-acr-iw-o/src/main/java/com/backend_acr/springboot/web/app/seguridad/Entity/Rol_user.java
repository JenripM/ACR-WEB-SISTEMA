package com.backend_acr.springboot.web.app.seguridad.Entity;


import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name="rol_users")
public class Rol_user {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(length = 45)
	private int id;
	
	@Column(nullable = false)
	private String descripcion;
	
	@Column(nullable = false)
	private int clientes;
	@Column(nullable = false)
	private int trabajadores;
	@Column(nullable = false)
	private int casos;
	@Column(nullable = false)
	private int tareas;
	@Column(nullable = false)
	private int docum_corres;
	@Column(nullable = false)
	private int accesos;
	
	public Rol_user() {
		
	}
	
	public Rol_user(int id, String descripcion, int clientes, int trabajadores, int casos, int tareas, int docum_corres,
			int accesos) {
		this.id = id;
		this.descripcion = descripcion;
		this.clientes = clientes;
		this.trabajadores = trabajadores;
		this.casos = casos;
		this.tareas = tareas;
		this.docum_corres = docum_corres;
		this.accesos = accesos;
	}


	public int getId() {
		return id;
	}


	public void setId(int id) {
		this.id = id;
	}


	public String getDescripcion() {
		return descripcion;
	}


	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}


	public int getClientes() {
		return clientes;
	}


	public void setClientes(int clientes) {
		this.clientes = clientes;
	}


	public int getTrabajadores() {
		return trabajadores;
	}


	public void setTrabajadores(int trabajadores) {
		this.trabajadores = trabajadores;
	}


	public int getCasos() {
		return casos;
	}


	public void setCasos(int casos) {
		this.casos = casos;
	}


	public int getTareas() {
		return tareas;
	}


	public void setTareas(int tareas) {
		this.tareas = tareas;
	}


	public int getDocum_corres() {
		return docum_corres;
	}


	public void setDocum_corres(int docum_corres) {
		this.docum_corres = docum_corres;
	}


	public int getAccesos() {
		return accesos;
	}


	public void setAccesos(int accesos) {
		this.accesos = accesos;
	}
	
	
}
