package ar.edu.unlam.tallerweb1.modelo;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

@Entity
public class DistribucionDetalle {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@ManyToOne
	private Establecimiento establecimiento;

	@ManyToOne
	private Insumo insumo;

	private Integer cantidad;

	public DistribucionDetalle() {
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Establecimiento getEstablecimiento() {
		return establecimiento;
	}

	public void setEstablecimiento(Establecimiento establecimiento) {
		this.establecimiento = establecimiento;
	}

	public Insumo getInsumo() {
		return insumo;
	}

	public void setInsumo(Insumo insumo) {
		this.insumo = insumo;
	}

	public Integer getCantidad() {
		return cantidad;
	}

	public void setCantidad(Integer cantidad) {
		this.cantidad = cantidad;
	}

}
