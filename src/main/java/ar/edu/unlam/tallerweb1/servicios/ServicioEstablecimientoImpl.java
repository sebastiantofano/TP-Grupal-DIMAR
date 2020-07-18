package ar.edu.unlam.tallerweb1.servicios;

import java.util.Comparator;
import java.util.List;

import org.hibernate.Hibernate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import ar.edu.unlam.tallerweb1.modelo.Establecimiento;
import ar.edu.unlam.tallerweb1.modelo.Insumo;
import ar.edu.unlam.tallerweb1.repositorios.RepositorioEstablecimiento;

@Service("servicioEstablecimiento")
@Transactional
public class ServicioEstablecimientoImpl implements ServicioEstablecimiento {

	private RepositorioEstablecimiento servicioEstablecimientoDao;

	// Inyección de dependencia
	@Autowired
	public ServicioEstablecimientoImpl(RepositorioEstablecimiento servicioEstablecimientoDao) {
		this.servicioEstablecimientoDao = servicioEstablecimientoDao;
	}

	@Override
	public Establecimiento consultarEstablecimiento(int id) {
		return servicioEstablecimientoDao.getById(id);
	}

	@Override
	public List<Establecimiento> obtenerTodos() {
		List<Establecimiento> establecimientos = servicioEstablecimientoDao.getAll();
		for (Establecimiento est : establecimientos) {
			if (est.getZona() != null) {			
				Hibernate.initialize(est.getZona().getProvincias());
			}
		}
		return establecimientos;
	}

	@Override
	public Long cantidadItems(List<Establecimiento> listaEstablecimiento) {
		return servicioEstablecimientoDao.cantidadItems(listaEstablecimiento);
	}

	@Override
	public Establecimiento establecimientoMayorOcupacion() {
		List<Establecimiento> totalEstablec = servicioEstablecimientoDao.getAll();
		Establecimiento eMayorOcupacion = totalEstablec.stream()
				.max(Comparator.comparing(Establecimiento::getOcupacion)).get();
		return eMayorOcupacion;
	}
}
