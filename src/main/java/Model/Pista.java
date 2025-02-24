package Model;

import java.math.BigDecimal;
import javax.persistence.*;

@Entity
@Table(name = "pistas")
public class Pista {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "nombre", nullable = false, length = 100)
    private String nombre;

    @Column(name = "descripción", length = 200)
    private String descripcion;

    @Column(name = "ubicacion", nullable = false, length = 255)
    private String ubicacion;

    @Column(name = "imagen_url", length = 255)
    private String imagenUrl;

    @Column(name = "tipo", nullable = false, length = 50)
    private String tipo;

    @Column(name = "estado", nullable = false, length = 50)
    private String estado = "disponible";

    @Column(name = "precio_por_hora", nullable = false, precision = 10, scale = 2)
    private BigDecimal precioPorHora;

    public Pista() {}

    public Pista(Long id, String nombre, String descripcion, String ubicacion, String imagenUrl, String tipo,
                 String estado, BigDecimal precioPorHora) {
        this.id = id;
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.ubicacion = ubicacion;
        this.imagenUrl = imagenUrl;
        this.tipo = tipo;
        this.estado = estado;
        this.precioPorHora = precioPorHora;
    }

    // Getters y Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getUbicacion() {
        return ubicacion;
    }

    public void setUbicacion(String ubicacion) {
        this.ubicacion = ubicacion;
    }

    public String getImagenUrl() {
        return imagenUrl;
    }

    public void setImagenUrl(String imagenUrl) {
        this.imagenUrl = imagenUrl;
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

    public BigDecimal getPrecioPorHora() {
        return precioPorHora;
    }

    public void setPrecioPorHora(BigDecimal precioPorHora) {
        this.precioPorHora = precioPorHora;
    }
}
