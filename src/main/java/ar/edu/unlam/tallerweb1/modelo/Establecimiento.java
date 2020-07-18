package ar.edu.unlam.tallerweb1.modelo;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Transient;

@Entity
public class Establecimiento {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	private String nombre;
	private String ubicacion;
	private Integer capacidad;
	private Integer ocupacion;

	@ManyToOne
	private Responsable responsable;

	@ManyToOne
	private Zona zona;

	// No persisten en la bbdd
	@Transient
	private Float prioridad;

	public Establecimiento() {
	}

	public Establecimiento(String nombre) {
		this.nombre = nombre;
	}

	public Establecimiento(String nombre, String ubicacion, Integer capacidad, Integer ocupacion, Zona zona,
			Responsable responsable) {
		this.nombre = nombre;
		this.ubicacion = ubicacion;
		this.capacidad = capacidad;
		this.ocupacion = ocupacion;
		this.zona = zona;
		this.responsable = responsable;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getId() {
		return id;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getUbicacion() {
		return ubicacion;
	}

	public void setUbicacion(String ubicacion) {
		this.ubicacion = ubicacion;
	}

	public Integer getCapacidad() {
		return capacidad;
	}

	public void setCapacidad(Integer capacidad) {
		this.capacidad = capacidad;
	}

	public Float getPrioridad() {
		return prioridad;
	}

	public void setPrioridad(Float prioridad) {
		this.prioridad = prioridad;
	}

	public Zona getZona() {
		return zona;
	}

	public Integer getOcupacion() {
		return ocupacion;
	}

	public void setOcupacion(Integer ocupacion) {
		this.ocupacion = ocupacion;
	}

	public void setZona(Zona zona) {
		this.zona = zona;
	}

	public Responsable getResponsable() {
		return responsable;
	}

	public void setResponsable(Responsable responsable) {
		this.responsable = responsable;
	}

}
