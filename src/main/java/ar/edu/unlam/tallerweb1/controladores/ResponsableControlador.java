package ar.edu.unlam.tallerweb1.controladores;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import ar.edu.unlam.tallerweb1.configuracion.Authorized;
import ar.edu.unlam.tallerweb1.modelo.Establecimiento;
import ar.edu.unlam.tallerweb1.servicios.ServicioEstablecimiento;
import ar.edu.unlam.tallerweb1.servicios.ServicioLogin;
import ar.edu.unlam.tallerweb1.servicios.ServicioResponsable;

@Authorized
@Controller
public class ResponsableControlador {

	private final ServicioResponsable servicioResponsable;
	private final ServicioEstablecimiento servicioEstablecimiento;
	private ServicioLogin servicioLogin;

	@Autowired
	public ResponsableControlador(ServicioResponsable servicioResponsable,
			ServicioEstablecimiento servicioEstablecimiento, ServicioLogin servicioLogin) {
		super();
		this.servicioResponsable = servicioResponsable;
		this.servicioEstablecimiento = servicioEstablecimiento;
		this.servicioLogin = servicioLogin;
	}

	@RequestMapping(path = "/responsables", method = RequestMethod.GET)
	public ModelAndView responsables() {
		ModelMap modelo = new ModelMap();

		List<Establecimiento> establecimientos = servicioEstablecimiento.obtenerTodos();
		modelo.put("establecimientos", establecimientos);

		return new ModelAndView("responsables", modelo);
	}

}
