package ar.edu.unlam.tallerweb1.repositorios;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import ar.edu.unlam.tallerweb1.modelo.Usuario;

// implelemtacion del repositorio de usuarios, la anotacion @Repository indica a Spring que esta clase es un componente que debe
// ser manejado por el framework, debe indicarse en applicationContext que busque en el paquete ar.edu.unlam.tallerweb1.dao
// para encontrar esta clase.
@Repository("repositorioUsuario")
public class RepositorioUsuarioImpl extends RepositorioBaseImpl<Usuario, Integer> implements RepositorioUsuario {

	@Autowired
	public RepositorioUsuarioImpl(SessionFactory sessionFactory) {
		super(sessionFactory);
	}

	@SuppressWarnings("deprecation")
	@Override
	public Usuario consultarUsuario(Usuario usuario) {

		// Se obtiene la sesion asociada a la transaccion iniciada en el servicio que
		// invoca a este metodo y se crea un criterio
		// de busqueda de Usuario donde el email y password sean iguales a los del
		// objeto recibido como parametro
		// uniqueResult da error si se encuentran más de un resultado en la busqueda.
		final Session session = this.sessionFactory.getCurrentSession();

		return (Usuario) session.createCriteria(Usuario.class).add(Restrictions.eq("email", usuario.getEmail()))
				.add(Restrictions.eq("password", usuario.getPassword())).uniqueResult();
	}

}
