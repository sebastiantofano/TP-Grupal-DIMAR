package ar.edu.unlam.tallerweb1.modelo;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import ar.edu.unlam.tallerweb1.comun.enums.TipoDeEstadoDistribucion;

@Entity
public class EstadoDistribucion {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	private String descripcion;

	public EstadoDistribucion() {
	}

	public EstadoDistribucion(TipoDeEstadoDistribucion estado) {
		this.id = estado.getId();
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

}
