package ar.edu.unlam.tallerweb1.modelo;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

@Entity
public class Responsable {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	private String nombre;
	private String apellido;
	private Integer edad;
	private String titulo;
	
	@ManyToOne
	private Zona zona;


	public Responsable() {
	}


	public String getNombre() {
		return nombre;
	}


	public void setNombre(String nombre) {
		this.nombre = nombre;
	}


	public String getApellido() {
		return apellido;
	}


	public void setApellido(String apellido) {
		this.apellido = apellido;
	}


	public Integer getEdad() {
		return edad;
	}


	public void setEdad(Integer edad) {
		this.edad = edad;
	}


	public Integer getId() {
		return id;
	}


	public String getTitulo() {
		return titulo;
	}


	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}
	
	
	public Zona getZona() {
		return zona;
	}


	public void setZona(Zona zona) {
		this.zona = zona;
	}
	
}
