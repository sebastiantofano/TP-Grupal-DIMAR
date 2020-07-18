package ar.edu.unlam.tallerweb1.modelo;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

@Entity
public class Zona {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	private String nombre;
	private Integer puntaje;
	
	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	private List<Provincia> provincias;
	
	public List<Provincia> getProvincias() {
		return provincias;
	}

	public void setProvincias(List<Provincia> provincias) {
		this.provincias = provincias;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public Integer getPuntaje() {
		return puntaje;
	}

	public void setPuntaje(Integer puntaje) {
		this.puntaje = puntaje;
	}

	public Integer getId() {
		return id;
	}

	
	
}
