package ar.edu.unlam.tallerweb1.controladores;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import ar.edu.unlam.tallerweb1.modelo.Usuario;
import ar.edu.unlam.tallerweb1.servicios.ServicioLogin;

@Controller
public class LoginControlador {

	// La anotacion @Autowired indica a Spring que se debe utilizar el contructor
	// como mecanismo de inyeccion de dependencias,
	// es decir, que lo parametros del mismo deben ser un bean de spring y el
	// framewrok automaticamente pasa como parametro
	// el bean correspondiente, en este caso, un objeto de una clase que implemente
	// la interface ServicioLogin, dicha clase debe estar anotada como @Service o
	// @Repository y debe estar en un
	// paquete de los indicados en applicationContext.xml
	private ServicioLogin servicioLogin;

	@Autowired
	public LoginControlador(ServicioLogin servicioLogin) {
		this.servicioLogin = servicioLogin;
	}

	// Este metodo escucha la URL localhost:8080/NOMBRE_APP/login si la misma es
	// invocada por metodo http GET
	@RequestMapping(value = { "/login" }, method = RequestMethod.GET)
	public ModelAndView irALogin(@RequestParam(value = "msg", required = false) String msg) {

		ModelMap modelo = new ModelMap();
		Usuario usuario = new Usuario();
		modelo.put("usuario", usuario);

		modelo.put("msg", servicioLogin.intentoIngresarSinPermisos(msg));
		return new ModelAndView("login", modelo);
	}

	// Este metodo escucha la URL validar-login siempre y cuando se invoque con
	// metodo http POST
	// El metodo recibe un objeto Usuario el que tiene los datos ingresados en el
	// form correspondiente y se corresponde con el modelAttribute definido en el
	// tag form:form
	@RequestMapping(path = "/validar-login", method = RequestMethod.POST)
	public ModelAndView validarLogin(@ModelAttribute("usuario") Usuario usuario, HttpServletRequest request) {
		ModelMap model = new ModelMap();

		Usuario usuarioBuscado = servicioLogin.consultarUsuario(usuario);
		if (usuarioBuscado != null) {
			request.getSession(true);
			request.getSession().setAttribute("ROL", usuarioBuscado.getRol());
			request.getSession().setAttribute("usuario", usuario.getEmail());
			return new ModelAndView("redirect:/home");
		} else {
			// si el usuario no existe agrega un mensaje de error en el modelo.
			model.put("error", "Usuario o contraseña incorrecta");
		}
		return new ModelAndView("login", model);
	}

	@RequestMapping(path = "/logout", method = RequestMethod.POST)
	public ModelAndView logout(HttpServletRequest request) {
		servicioLogin.cerrarSesion();

		ModelMap model = new ModelMap();
		Usuario usuario = new Usuario();
		model.put("usuario", usuario);

		String[] msg = new String[2];
		msg[0] = ("Ha cerrado la sesión correctamente");
		msg[1] = ("success");
		model.put("msg", msg);

		return new ModelAndView("login", model);
	}

	@RequestMapping(path = "/IniciarsesionDesarrollo", method = RequestMethod.POST)
	public ModelAndView IniciarsesionDesarrollo() {
		servicioLogin.IniciarsesionDesarrollo();
		return new ModelAndView("redirect:home");
	}
}
