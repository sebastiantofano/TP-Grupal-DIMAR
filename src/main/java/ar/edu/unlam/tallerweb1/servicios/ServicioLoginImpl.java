package ar.edu.unlam.tallerweb1.servicios;

import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;

//La anotacion @Service indica a Spring que esta clase es un componente que debe ser manejado por el framework, debe indicarse en applicationContext que busque en el paquete ar.edu.unlam.tallerweb1.servicios para encontrar esta clase.
//La anotacion @Transactional indica que se debe iniciar una transaccion de base de datos ante la invocacion de cada metodo del servicio, dicha transaccion esta asociada al transaction manager definido en el archivo spring-servlet.xml y el mismo asociado al session factory definido en hibernateContext.xml. De esta manera todos los metodos de cualquier dao invocados dentro de un servicio se ejecutan en la misma transaccion
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import ar.edu.unlam.tallerweb1.modelo.Usuario;
import ar.edu.unlam.tallerweb1.repositorios.RepositorioUsuario;

@Service("servicioLogin")
@Transactional
public class ServicioLoginImpl implements ServicioLogin {

	private RepositorioUsuario servicioLoginDao;
	
	@Inject
	private HttpServletRequest request;
	
	@Autowired
	public ServicioLoginImpl(RepositorioUsuario servicioLoginDao) {
		this.servicioLoginDao = servicioLoginDao;
	}

	@Override
	public Usuario consultarUsuario(Usuario usuario) {
		return servicioLoginDao.consultarUsuario(usuario);
	}

	
	public boolean verificarSesionActiva() {
		return this.request.getSession().getAttribute("usuario") != null ? true : false;
	}

	@Override
	public void IniciarsesionDesarrollo() {
		request.getSession().setAttribute("usuario", "sesionDesarrollo");
	}

	@Override
	public void cerrarSesion() {
		this.request.getSession().invalidate();
	}

	@Override
	public String[] intentoIngresarSinPermisos(String msg) {
		String[] sinAutorizacion = new String[2];
		if(msg != null) {
			sinAutorizacion[0] ="Sin Autorización para acceder";
			sinAutorizacion[1] ="danger";
		}
		return sinAutorizacion;
	}
}
