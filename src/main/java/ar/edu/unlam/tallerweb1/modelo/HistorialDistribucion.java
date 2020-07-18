package ar.edu.unlam.tallerweb1.modelo;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

@Entity
public class HistorialDistribucion {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;

	private String descripcion;

	private LocalDate fecha;

	private String usuario;

	@ManyToOne(fetch = FetchType.LAZY)
	private Distribucion distribucion;

	public HistorialDistribucion() {
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

	public LocalDate getFecha() {
		return fecha;
	}

	public void setFecha(LocalDate fecha) {
		this.fecha = fecha;
	}

	public String getUsuario() {
		return usuario;
	}

	public void setUsuario(String usuario) {
		this.usuario = usuario;
	}

	public Distribucion getDistribucion() {
		return distribucion;
	}

	public void setDistribucion(Distribucion distribucion) {
		this.distribucion = distribucion;
	}
}
