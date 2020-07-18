package ar.edu.unlam.tallerweb1.repositorios;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import ar.edu.unlam.tallerweb1.modelo.Responsable;

@Repository("repositorioResponsable")
public class RepositorioResponsableImpl extends RepositorioBaseImpl<Responsable, Integer> implements RepositorioResponsable{

	@Autowired
	public RepositorioResponsableImpl(SessionFactory sessionFactory) {
		super(sessionFactory);
	}

}
