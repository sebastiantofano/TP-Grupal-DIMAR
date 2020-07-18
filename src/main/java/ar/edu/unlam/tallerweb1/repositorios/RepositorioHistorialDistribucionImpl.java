package ar.edu.unlam.tallerweb1.repositorios;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import ar.edu.unlam.tallerweb1.modelo.HistorialDistribucion;

@Repository
public class RepositorioHistorialDistribucionImpl extends RepositorioBaseImpl<HistorialDistribucion, Integer>
		implements RepositorioHistorialDistribucion {

	@Autowired
	public RepositorioHistorialDistribucionImpl(SessionFactory sessionFactory) {
		super(sessionFactory);
	}

	@Override
	public void guardar(HistorialDistribucion historial) {
		final Session session = sessionFactory.getCurrentSession();
		session.save(historial);
	}

	@Override
	public List<HistorialDistribucion> obtenerPorDistribucionId(Long id) {
		final Session session = sessionFactory.getCurrentSession();
		return session.createCriteria(HistorialDistribucion.class, "historial")
				.createAlias("historial.distribucion", "distribucion").add(Restrictions.eq("distribucion.id", id))
				.list();
	}
}
