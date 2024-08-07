package cl.javadevs.springsecurityjwt.dtos;

import java.util.Date;

public class CasoDTO {
    private int Id;
    private String tipo;
    private String estado;
    private Date fecha_inicio;
    private Date fecha_cierre;
    private String descripcion;

    public int getId() {
        return Id;
    }
    public void setId(int id) {
        Id = id;
    }
    public String getTipo() {
        return tipo;
    }
    public void setTipo(String tipo) {
        this.tipo = tipo;
    }
    public String getEstado() {
        return estado;
    }
    public void setEstado(String estado) {
        this.estado = estado;
    }
    public Date getFecha_inicio() {
        return fecha_inicio;
    }
    public void setFecha_inicio(Date fecha_inicio) {
        this.fecha_inicio = fecha_inicio;
    }
    public Date getFecha_cierre() {
        return fecha_cierre;
    }
    public void setFecha_cierre(Date fecha_cierre) {
        this.fecha_cierre = fecha_cierre;
    }
    public String getDescripcion() {
        return descripcion;
    }
    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }
}
