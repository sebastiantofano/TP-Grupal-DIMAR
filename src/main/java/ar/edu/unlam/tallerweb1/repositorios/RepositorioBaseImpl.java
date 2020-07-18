package ar.edu.unlam.tallerweb1.repositorios;

import java.io.Serializable;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.List;

import javax.transaction.Transactional;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Projections;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

//Repositorio Genérico
@Repository("repositorioBase")
@Transactional
public abstract class RepositorioBaseImpl<TEntity extends Object, TId extends Serializable>
		implements RepositorioBase<TEntity, TId> {

	protected SessionFactory sessionFactory;
	// Variable del tipo class de una entity
	protected Class<TEntity> type;

	@SuppressWarnings("unchecked")
	@Autowired
	public RepositorioBaseImpl(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
		Type type = getClass().getGenericSuperclass();
		this.type = (Class<TEntity>) ((ParameterizedType) type).getActualTypeArguments()[0];
	}

	// Implementación del método para obtener un objeto de una clase por id
	public TEntity getById(final TId id) {
		final Session session = sessionFactory.getCurrentSession();
		TEntity objeto = (TEntity) session.get(this.type, id);
		return objeto;
	}

	// Implementación del método para obtener una lista de objetos de una clase
	public List<TEntity> getAll() {
		final Session session = sessionFactory.getCurrentSession();
		List<TEntity> listaObjetos = session.createCriteria(this.type).list();
		return listaObjetos;
	}

	// Implementación del método para guardar un objeto de una clase
	// No debería
	public TId save(TEntity objeto) {
		final Session session = sessionFactory.getCurrentSession();
		return (TId) session.save(objeto);
	}

	// Implementación del método para borrar un objeto de una clase
	public void delete(TEntity objeto) {
		final Session session = sessionFactory.getCurrentSession();
		session.delete(objeto);
	}

	// Implementación del método para actualizar un objeto de una clase
	public void update(TEntity objeto) {
		final Session session = sessionFactory.getCurrentSession();
		session.update(objeto);
	}

	public Long cantidadItems(List<TEntity> listaEntity) {
		final Session session = this.sessionFactory.getCurrentSession();
		return (Long) session.createCriteria(this.type).setProjection(Projections.rowCount()).uniqueResult();
	}

}
