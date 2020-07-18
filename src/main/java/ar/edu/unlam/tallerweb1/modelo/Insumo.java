package ar.edu.unlam.tallerweb1.modelo;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Transient;

@Entity
public class Insumo {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String nombre;
	private String tipo;
	private Integer cantidad;
	private Float precioUnidad;
	
//	No persisten en la bbdd
	@Transient
	private Float pxq;

	// Constructor vacío
	public Insumo() {

	}


	public Insumo(Long id, String nombre, String tipo, Integer cantidad, Float precioUnidad) {
		this.id = id;
		this.nombre = nombre;
		this.tipo = tipo;
		this.cantidad = cantidad;
		this.precioUnidad = precioUnidad;
	}


	public Long getId() {
		return id;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getTipo() {
		return tipo;
	}

	public void setTipo(String tipo) {
		this.tipo = tipo;
	}

	public Integer getCantidad() {
		return cantidad;
	}

	public void setCantidad(Integer cantidad) {
		this.cantidad = cantidad;
	}
	


	public Float getPrecioUnidad() {
		return precioUnidad;
	}


	public void setPrecioUnidad(Float precioUnidad) {
		this.precioUnidad = precioUnidad;
	}


	public Float getPxq() {
		return (float)this.precioUnidad * (float)this.cantidad;
	}


	public void setId(Long id) {
		this.id = id;
	}


	public void setPxq(Float pxq) {
		this.pxq = pxq;
	}

	
}
