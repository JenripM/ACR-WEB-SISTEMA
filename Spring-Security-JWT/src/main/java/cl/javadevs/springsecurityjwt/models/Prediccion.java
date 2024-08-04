package cl.javadevs.springsecurityjwt.models;

import java.util.Date;
import jakarta.persistence.*;

@Entity
@Table(name = "prediccion")
public class Prediccion {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(length = 45)
    private Integer id;

    @Column(name = "id_cliente")
    private Integer idCliente;

    @Column
    private Integer edad;

    @Column
    private String genero;

    @Column(name = "tipo_caso")
    private String tipoCaso;

    @Column(name = "fecha_inicio")
    @Temporal(TemporalType.DATE)
    private Date fechaInicio;

    @Column(name = "fecha_cierre")
    @Temporal(TemporalType.DATE)
    private Date fechaCierre;

    @Column(name = "ultima_actividad")
    @Temporal(TemporalType.DATE)
    private Date ultimaActividad;

    @Column
    private Integer prediccion;

    // Getters and Setters
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getIdCliente() {
        return idCliente;
    }

    public void setIdCliente(Integer idCliente) {
        this.idCliente = idCliente;
    }

    public Integer getEdad() {
        return edad;
    }

    public void setEdad(Integer edad) {
        this.edad = edad;
    }

    public String getGenero() {
        return genero;
    }

    public void setGenero(String genero) {
        this.genero = genero;
    }

    public String getTipoCaso() {
        return tipoCaso;
    }

    public void setTipoCaso(String tipoCaso) {
        this.tipoCaso = tipoCaso;
    }

    public Date getFechaInicio() {
        return fechaInicio;
    }

    public void setFechaInicio(Date fechaInicio) {
        this.fechaInicio = fechaInicio;
    }

    public Date getFechaCierre() {
        return fechaCierre;
    }

    public void setFechaCierre(Date fechaCierre) {
        this.fechaCierre = fechaCierre;
    }

    public Date getUltimaActividad() {
        return ultimaActividad;
    }

    public void setUltimaActividad(Date ultimaActividad) {
        this.ultimaActividad = ultimaActividad;
    }

    public Integer getPrediccion() {
        return prediccion;
    }

    public void setPrediccion(Integer prediccion) {
        this.prediccion = prediccion;
    }
}
