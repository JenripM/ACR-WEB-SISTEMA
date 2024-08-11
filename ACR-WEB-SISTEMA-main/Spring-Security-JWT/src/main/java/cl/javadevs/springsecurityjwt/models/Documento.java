package cl.javadevs.springsecurityjwt.models;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
@Entity
@Table(name = "DOCUMENTO")
public class Documento {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idDocumento;

    private String tipo;
    private String nombreArchivo; // Nombre del archivo guardado
    private byte[] datos;

    @ManyToOne
    @JoinColumn(name = "caso_id", nullable = false)
    private Caso caso;  // Asociación con el caso

    private String rutaArchivo;  // Ruta del archivo si se almacena en disco

    // Getters y Setters
    public Long getIdDocumento() {
        return idDocumento;
    }

    public void setIdDocumento(Long idDocumento) {
        this.idDocumento = idDocumento;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }
    public String getNombreArchivo() {
        return nombreArchivo;
    }

    public void setNombreArchivo(String nombreArchivo) {
        this.nombreArchivo = nombreArchivo;
    }

    public byte[] getDatos() {
        return datos;
    }

    public void setDatos(byte[] datos) {
        this.datos = datos;
    }

    public Caso getCaso() {
        return caso;
    }

    public void setCaso(Caso caso) {
        this.caso = caso;
    }

    public String getRutaArchivo() {
        return rutaArchivo;
    }

    public void setRutaArchivo(String rutaArchivo) {
        this.rutaArchivo = rutaArchivo;
    }

}
