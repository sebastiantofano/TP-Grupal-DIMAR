package ar.edu.unlam.tallerweb1.repositorios;

import java.util.List;
import java.util.Map;

import ar.edu.unlam.tallerweb1.modelo.Distribucion;
import ar.edu.unlam.tallerweb1.modelo.Establecimiento;
import ar.edu.unlam.tallerweb1.modelo.Insumo;

public interface RepositorioDistribucion extends RepositorioBase<Distribucion, Long> {
	List<Distribucion> totalDistribucionesPorTipo();

	void guardarDistribucion(Distribucion distribucion);

	Distribucion obtenerDistribucion(Long id);
}
