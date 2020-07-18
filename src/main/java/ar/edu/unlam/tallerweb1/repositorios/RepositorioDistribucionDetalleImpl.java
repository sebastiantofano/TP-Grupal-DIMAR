package ar.edu.unlam.tallerweb1.repositorios;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import ar.edu.unlam.tallerweb1.modelo.Distribucion;
import ar.edu.unlam.tallerweb1.modelo.DistribucionDetalle;

@Repository
public class RepositorioDistribucionDetalleImpl extends RepositorioBaseImpl<DistribucionDetalle, Long>
		implements RepositorioDistribucionDetalle {

	@Autowired
	public RepositorioDistribucionDetalleImpl(SessionFactory sessionFactory) {
		super(sessionFactory);
	}

	@Override
	public void guardarDetalle(DistribucionDetalle detalle) {
		final Session session = sessionFactory.getCurrentSession();
		session.save(detalle);
	}

	@Override
	public List<DistribucionDetalle> obtenerPorDistribucionId(Long id) {
		final Session session = sessionFactory.getCurrentSession();
		return session.createCriteria(DistribucionDetalle.class, "detalle")
				.createAlias("detalle.distribucion", "distribucion")
				.add(Restrictions.eq("distribucion.id", id))
				.list();
	}
}
