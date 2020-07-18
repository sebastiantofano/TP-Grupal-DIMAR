package ar.edu.unlam.tallerweb1.repositorios;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import ar.edu.unlam.tallerweb1.modelo.Establecimiento;

@Repository("repositorioEstablecimiento")
public class RepositorioEstablecimientoImpl extends RepositorioBaseImpl<Establecimiento, Integer>
		implements RepositorioEstablecimiento {

	@Autowired
	public RepositorioEstablecimientoImpl(SessionFactory sessionFactory) {
		super(sessionFactory);
	}

}
