package Model;
import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "pistas")
public class Pista {
    
    @Id
    private Long id;
    
    @Column(name ="nombre")
    private String nombre;
    
    @Column(name = "descripcion")
    private String descripcion;
    
    @Column(name = "ubicacion")
    private String ubicacion;
    
    @Column(name = "imageUrl")
    private String imagenUrl;
    
    @Column(name = "tipo")
    private String tipo;
    
    @Column(name = "estado")
    private String estado = "disponible";
    
    private int precioPorHora;
    
    
    
    public Pista() {
		super();
	}

	public Pista(Long id, String nombre, String descripcion, String ubicacion, String imagenUrl, String tipo,
			String estado, int precioPorHora) {
		super();
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

	public int getPrecioPorHora() {
		return precioPorHora;
	}

	public void setPrecioPorHora(int precioPorHora) {
		this.precioPorHora = precioPorHora;
	}
    

}
