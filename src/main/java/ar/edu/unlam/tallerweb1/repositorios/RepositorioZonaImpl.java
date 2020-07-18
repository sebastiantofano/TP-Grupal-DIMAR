package ar.edu.unlam.tallerweb1.repositorios;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import ar.edu.unlam.tallerweb1.modelo.Zona;

@Repository("repositorioZona")
public class RepositorioZonaImpl extends RepositorioBaseImpl<Zona, Integer> implements RepositorioZona {
	@Autowired
	public RepositorioZonaImpl(SessionFactory sessionFactory) {
		super(sessionFactory);
	}
}
