package com.backend_acr.springboot.web.app.actividad;

import com.backend_acr.springboot.web.app.trabajador.Trabajador;

public class Actividad {

    public String IdCaso;
    public Trabajador trabajador;
    public String NombreActv;
    public int prioridad;
    public int tiempo;
    
    
    public Actividad() {
    }

    public Actividad(String IdCaso, Trabajador trabajador, String NombreActv, int prioridad, int tiempo) {
        this.IdCaso = IdCaso;
        this.trabajador = trabajador;
        this.NombreActv = NombreActv;
        this.prioridad = prioridad;
        this.tiempo = tiempo;
    }
    
    public String getIdCaso() {
        return IdCaso;
    }

    public void setIdCaso(String IdCaso) {
        this.IdCaso = IdCaso;
    }

    // Getter y Setter para trabajador
    public Trabajador getTrabajador() {
        return trabajador;
    }

    public void setTrabajador(Trabajador trabajador) {
        this.trabajador = trabajador;
    }

    // Getter y Setter para NombreActv
    public String getNombreActv() {
        return NombreActv;
    }

    public void setNombreActv(String NombreActv) {
        this.NombreActv = NombreActv;
    }

    // Getter y Setter para prioridad
    public int getPrioridad() {
        return prioridad;
    }

    public void setPrioridad(int prioridad) {
        this.prioridad = prioridad;
    }

    // Getter y Setter para tiempo
    public int getTiempo() {
        return tiempo;
    }

    public void setTiempo(int tiempo) {
        this.tiempo = tiempo;
    }
}

}
