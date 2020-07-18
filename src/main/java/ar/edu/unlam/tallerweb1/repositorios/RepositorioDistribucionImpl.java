package ar.edu.unlam.tallerweb1.repositorios;

import java.util.List;
import java.util.Map;

import org.hibernate.Hibernate;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Projections;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import ar.edu.unlam.tallerweb1.modelo.Distribucion;
import ar.edu.unlam.tallerweb1.modelo.DistribucionDetalle;
import ar.edu.unlam.tallerweb1.modelo.Establecimiento;
import ar.edu.unlam.tallerweb1.modelo.Insumo;

@Repository
public class RepositorioDistribucionImpl extends RepositorioBaseImpl<Distribucion, Long>
		implements RepositorioDistribucion {

	@Autowired
	public RepositorioDistribucionImpl(SessionFactory sessionFactory) {
		super(sessionFactory);
	}

	@Override
	public void guardarDistribucion(Distribucion distribucion) {
		final Session session = sessionFactory.getCurrentSession();
		session.save(distribucion);
	}

	@Override
	public Distribucion obtenerDistribucion(Long id) {
		final Session session = sessionFactory.getCurrentSession();

		Distribucion distribucion = session.find(Distribucion.class, id);

		if (distribucion != null) {
			// Get Lazy Model
			Hibernate.initialize(distribucion.getDetalles());
		}

		return distribucion;
	}

	@SuppressWarnings({ "unchecked", "deprecation" })
	@Override
	public List<Distribucion> totalDistribucionesPorTipo() {
		final Session session = sessionFactory.getCurrentSession();
		return session.createCriteria(Distribucion.class, "dd").createAlias("dd.tipoDistribucion", "td").setProjection(
				Projections.projectionList().add(Projections.groupProperty("td.id")).add(Projections.count("td.id")))
				.list();
	}
}
