package ar.edu.unlam.tallerweb1.servicios;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ar.edu.unlam.tallerweb1.modelo.DistribucionDetalle;
import ar.edu.unlam.tallerweb1.repositorios.RepositorioDistribucionDetalle;

@Service
public class ServicioDistribucionDetalleImpl implements ServicioDistribucionDetalle {

	private RepositorioDistribucionDetalle repositorioDistribucionDetalle;

	@Autowired
	public ServicioDistribucionDetalleImpl(RepositorioDistribucionDetalle repositorioDistribucionDetalle) {
		this.repositorioDistribucionDetalle = repositorioDistribucionDetalle;
	}

	@Override
	public List<DistribucionDetalle> obtenerDistribucionesDetalles() {
		return repositorioDistribucionDetalle.getAll();
	}

	@Override
	public List<DistribucionDetalle> obtenerDistribucionDetallerPorDistribucionId(Long id) {
		return repositorioDistribucionDetalle.obtenerPorDistribucionId(id);
	}
}
