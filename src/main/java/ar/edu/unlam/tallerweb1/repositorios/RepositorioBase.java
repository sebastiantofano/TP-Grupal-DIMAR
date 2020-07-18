package ar.edu.unlam.tallerweb1.repositorios;

import java.io.Serializable;
import java.util.List;

//Interface Genérica
public interface RepositorioBase<TEntity extends Object, TId extends Serializable> {

	// Devuelve un objeto de una entidad por id
	TEntity getById(TId id);

	// Devuelve lista de todos los objetos de la entidad
	List<TEntity> getAll();

	// Guarda una entidad y devuelve su id
	TId save(TEntity entity);

	// Borra un objeto de una entidad
	void delete(TEntity entity);

	// Actualiza un objeto de una entidad
	void update(TEntity entity);

	// Permite obtener la cantidad de Items que tiene la lista
	Long cantidadItems(List<TEntity> listaEntity);
}
