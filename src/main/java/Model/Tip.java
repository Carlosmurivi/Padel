package Model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="ayudas")
public class Tip {
	
	// ATRIBUTOS
	@Id
	private int id;
	
	@Column(name="titulo")
	private String titulo;
	
	@Column(name="descripcion")
	private String descripcion;

	@Column(name="enlace")
	private String enlace;
	
	@Column(name="categoria_id")
	private int categoria_id;
	
	@Column(name="imagen")
	private String imagen;
	
	

	// CONSTRUCTOR
	public Tip() {
	}
	
	public Tip(int id, String titulo, String imagen) {
		this.id = id;
		this.titulo = titulo;
		this.imagen = imagen;
	}
	
	public Tip(int id, String titulo, String descripcion, String enlace, int categoria_id, String imagen) {
		this.id = id;
		this.titulo = titulo;
		this.descripcion = descripcion;
		this.enlace = enlace;
		this.categoria_id = categoria_id;
		this.imagen = imagen;
	}
	

	
	// GETTERS AND SETTERS
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getTitulo() {
		return titulo;
	}

	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public String getEnlace() {
		return enlace;
	}

	public void setEnlace(String enlace) {
		this.enlace = enlace;
	}

	public int getCategoria_id() {
		return categoria_id;
	}

	public void setCategoria_id(int categoria_id) {
		this.categoria_id = categoria_id;
	}

	public String getImagen() {
		return imagen;
	}

	public void setImagen(String imagen) {
		this.imagen = imagen;
	}
}
