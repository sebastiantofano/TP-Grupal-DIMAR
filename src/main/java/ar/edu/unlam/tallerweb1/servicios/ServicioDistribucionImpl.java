package ar.edu.unlam.tallerweb1.servicios;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import ar.edu.unlam.tallerweb1.comun.enums.TipoDeEstadoDistribucion;
import ar.edu.unlam.tallerweb1.comun.enums.TipoDeStrategy;
import ar.edu.unlam.tallerweb1.modelo.Distribucion;
import ar.edu.unlam.tallerweb1.modelo.DistribucionDetalle;
import ar.edu.unlam.tallerweb1.modelo.Establecimiento;
import ar.edu.unlam.tallerweb1.modelo.EstadoDistribucion;
import ar.edu.unlam.tallerweb1.modelo.HistorialDistribucion;
import ar.edu.unlam.tallerweb1.modelo.Insumo;
import ar.edu.unlam.tallerweb1.modelo.TipoDistribucion;
import ar.edu.unlam.tallerweb1.repositorios.RepositorioDistribucion;
import ar.edu.unlam.tallerweb1.repositorios.RepositorioDistribucionDetalle;
import ar.edu.unlam.tallerweb1.repositorios.RepositorioHistorialDistribucion;

@Service
@Transactional
public class ServicioDistribucionImpl implements ServicioDistribucion {

	private RepositorioDistribucion repositorioDistribucion;
	private RepositorioDistribucionDetalle repositorioDistribucionDetalle;
	private RepositorioHistorialDistribucion repositorioHistorialDistribucion;

	@Inject
	private HttpServletRequest request;

	// Inyección de dependencia
	@Autowired
	public ServicioDistribucionImpl(RepositorioDistribucion repositorioDistribucion,
			RepositorioDistribucionDetalle repositorioDistribucionDetalle,
			RepositorioHistorialDistribucion repositorioHistorialDistribucion) {
		this.repositorioDistribucion = repositorioDistribucion;
		this.repositorioDistribucionDetalle = repositorioDistribucionDetalle;
		this.repositorioHistorialDistribucion = repositorioHistorialDistribucion;
	}

	// GetAll
	@Override
	public List<Distribucion> obtenerDistribuciones() {
		return repositorioDistribucion.getAll();
	}

	// GetById
	@Override
	public Distribucion obtenerPorId(Long id) {
		return repositorioDistribucion.getById(id);
	}

	// GetById With LazyEntities
	@Override
	public Distribucion obtenerConDetallesPorId(Long id) {
		return repositorioDistribucion.obtenerDistribucion(id);
	}

	// GetHistorial
	@Override
	public List<HistorialDistribucion> obtenerHistorial(Long id) {
		return repositorioHistorialDistribucion.obtenerPorDistribucionId(id);
	}

	// Count
	@Override
	public List<Distribucion> totalDistribucionesPorTipo() {
		return this.repositorioDistribucion.totalDistribucionesPorTipo();
	}

	// Save
	@Override
	public void guardarDistribucion(Map<Establecimiento, List<Insumo>> calculoDeDistribucion,
			TipoDeStrategy tipoDeStrategy) {

		// Podemos agregar mas detalles a la distribucion
		Distribucion distribucion = new Distribucion();
		TipoDistribucion tipoDistribucion = new TipoDistribucion();
		EstadoDistribucion estadoDistribucion = new EstadoDistribucion(TipoDeEstadoDistribucion.PENDIENTE_REVISION);

		// Guardo el tipo de distribucion como detalle
		tipoDistribucion.setId((long) tipoDeStrategy.getId());

		distribucion.setFechaSolicitud(LocalDate.now());
		distribucion.setTipoDistribucion(tipoDistribucion);
		distribucion.setEstado(estadoDistribucion);

		// Si la creación de las instancias de los objetos auxiliares no están en este
		// órden, se cargan mal a la base, POR QUE???
		for (Entry<Establecimiento, List<Insumo>> establec_listaInsumo : calculoDeDistribucion.entrySet()) {
			for (Insumo itemInsumo : establec_listaInsumo.getValue()) {
				// Creo un insumo auxilir, no puedo usar directamente itemInsumo porque falla
				Insumo insumoAAsignar = new Insumo();
				insumoAAsignar.setId(itemInsumo.getId());

				// Creo el objeto distribucion y le asigno sus atributos para ser cargados como
				// un registro
				DistribucionDetalle detalle = new DistribucionDetalle();
				detalle.setEstablecimiento(establec_listaInsumo.getKey());
				detalle.setInsumo(insumoAAsignar);
				detalle.setCantidad(itemInsumo.getCantidad());

				// Persisto el registro de la asignacion de una cantidad de un Insumo a un
				// Establecimiento
				distribucion.addDetalle(detalle);
			}
		}

		// Persisto la distribucion
		repositorioDistribucion.guardarDistribucion(distribucion);

		this.crearNuevoHistorial("Nueva solicitud recibida.", distribucion);
	}

	@Override
	public void actualizarEstado(Distribucion distribucion, TipoDeEstadoDistribucion estado) {

		distribucion.setEstado(new EstadoDistribucion(estado));

		if (estado == TipoDeEstadoDistribucion.PENDIENTE_COORDINACION) {
			distribucion.setFechaRevision(LocalDate.now());
		}

		if (estado == TipoDeEstadoDistribucion.ENTREGADO) {
			distribucion.setFechaEntrega(LocalDate.now());
		}

		this.repositorioDistribucion.update(distribucion);

		this.crearNuevoHistorial("Se actualizo el estado a: " + estado.getDescripcion(), distribucion);
	}

	// Save
	@Override
	public void guardarHistorial(HistorialDistribucion historial) {
		this.repositorioHistorialDistribucion.save(historial);
	}

	private void crearNuevoHistorial(String descripcion, Distribucion distribucion) {
		HistorialDistribucion historial = new HistorialDistribucion();
		historial.setDescripcion(descripcion);
		historial.setFecha(LocalDate.now());
		historial.setDistribucion(distribucion);
		// Cambiar esto
		historial.setUsuario(this.request.getSession().getAttribute("usuario").toString());

		this.guardarHistorial(historial);
	}
}
