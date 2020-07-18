package ar.edu.unlam.tallerweb1.servicios;

import java.util.List;

import ar.edu.unlam.tallerweb1.modelo.DistribucionDetalle;

public interface ServicioDistribucionDetalle {

	List<DistribucionDetalle> obtenerDistribucionesDetalles();	

	List<DistribucionDetalle> obtenerDistribucionDetallerPorDistribucionId(Long id);
}
