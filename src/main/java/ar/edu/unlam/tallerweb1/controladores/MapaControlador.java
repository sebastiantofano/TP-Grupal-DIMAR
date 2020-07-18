package ar.edu.unlam.tallerweb1.controladores;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import ar.edu.unlam.tallerweb1.configuracion.Authorized;
import ar.edu.unlam.tallerweb1.modelo.Establecimiento;
import ar.edu.unlam.tallerweb1.servicios.ServicioEstablecimiento;
import ar.edu.unlam.tallerweb1.servicios.ServicioLogin;

@Authorized
@Controller()
public class MapaControlador {

	private final ServicioEstablecimiento servicioEstablecimiento;
	private ServicioLogin servicioLogin;

	@Autowired
	public MapaControlador(ServicioEstablecimiento servicioEstablecimiento, ServicioLogin servicioLogin) {
		this.servicioEstablecimiento = servicioEstablecimiento;
		this.servicioLogin = servicioLogin;
	}

	@RequestMapping(path = "/mapa", method = RequestMethod.GET)
	public ModelAndView irAMapa() {
		ModelMap modelo = new ModelMap();
		return new ModelAndView("mapa", modelo);
	}

	@RequestMapping(path = "/getData", method = RequestMethod.GET)
	public @ResponseBody List<Establecimiento> obtenerJSONMapa() {
		List<Establecimiento> establecimientos = new ArrayList<Establecimiento>();
		try {
			establecimientos = servicioEstablecimiento.obtenerTodos();
		} catch (Exception ex) {

		}
		return establecimientos;
	}

}