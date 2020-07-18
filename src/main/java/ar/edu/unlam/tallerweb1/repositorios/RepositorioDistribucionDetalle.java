package ar.edu.unlam.tallerweb1.repositorios;

import java.util.List;

import ar.edu.unlam.tallerweb1.modelo.DistribucionDetalle;

public interface RepositorioDistribucionDetalle extends RepositorioBase<DistribucionDetalle, Long> {

	void guardarDetalle(DistribucionDetalle detalle);

	List<DistribucionDetalle> obtenerPorDistribucionId(Long id);
}
