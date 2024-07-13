package cl.javadevs.springsecurityjwt.models;

import java.util.Date;

import jakarta.persistence.*;

@Entity
@Table(name = "casos")
public class Caso {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(length = 45)
    private int id;

    @Column(nullable = false)
    private String tipo;

    @Column(nullable = false)
    private String estado;

    @Column(nullable = true)
    private Date fecha_inicio;

    @Column(nullable = true)
    private Date fecha_cierre;

    @Column(nullable = false)
    private String descripcion;

    @ManyToOne
    @JoinColumn(name = "cliente_id", nullable = false)
    private Cliente cliente;

    public Caso() {
    }

    public Caso(int id, String tipo, String estado, Date fecha_inicio, Date fecha_cierre, String descripcion,
            Cliente cliente) {
        this.id = id;
        this.tipo = tipo;
        this.estado = estado;
        this.fecha_inicio = fecha_inicio;
        this.fecha_cierre = fecha_cierre;
        this.descripcion = descripcion;
        this.cliente = cliente;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }
}
