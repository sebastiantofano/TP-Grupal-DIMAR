package ar.edu.unlam.tallerweb1.servicios;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import ar.edu.unlam.tallerweb1.modelo.Responsable;
import ar.edu.unlam.tallerweb1.repositorios.RepositorioResponsable;

@Service("servicioResponsable")
@Transactional
public class ServicioResponsableImpl  implements ServicioResponsable{

	private RepositorioResponsable servicioResponsableoDao;

	// Inyección de dependencia
	@Autowired
	public ServicioResponsableImpl(RepositorioResponsable servicioResponsableoDao) {
		this.servicioResponsableoDao = servicioResponsableoDao;
	}
	
	@Override
	public List<Responsable> obtenerTodos() {
		return servicioResponsableoDao.getAll();
	}

}
