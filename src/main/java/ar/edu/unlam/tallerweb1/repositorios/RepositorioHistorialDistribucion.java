package ar.edu.unlam.tallerweb1.repositorios;

import java.util.List;

import ar.edu.unlam.tallerweb1.modelo.HistorialDistribucion;

public interface RepositorioHistorialDistribucion extends RepositorioBase<HistorialDistribucion, Integer> {

	void guardar(HistorialDistribucion historial);

	List<HistorialDistribucion> obtenerPorDistribucionId(Long id);
}
