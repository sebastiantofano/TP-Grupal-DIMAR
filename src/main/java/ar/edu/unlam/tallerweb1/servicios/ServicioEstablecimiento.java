package ar.edu.unlam.tallerweb1.servicios;

import java.util.List;

import ar.edu.unlam.tallerweb1.modelo.Establecimiento;

public interface ServicioEstablecimiento {

	Establecimiento consultarEstablecimiento(int id);

	List<Establecimiento> obtenerTodos();

	Long cantidadItems(List<Establecimiento> establecimientos);
	
	Establecimiento establecimientoMayorOcupacion();

}
