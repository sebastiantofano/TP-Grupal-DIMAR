package ar.edu.unlam.tallerweb1.controladores;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import ar.edu.unlam.tallerweb1.comun.enums.TipoDeStrategy;
import ar.edu.unlam.tallerweb1.configuracion.Authorized;
import ar.edu.unlam.tallerweb1.configuracion.StringToTipoDeStrategy;
import ar.edu.unlam.tallerweb1.modelo.Establecimiento;
import ar.edu.unlam.tallerweb1.modelo.Insumo;
import ar.edu.unlam.tallerweb1.servicios.ServicioEstablecimiento;
import ar.edu.unlam.tallerweb1.servicios.ServicioInsumo;
import ar.edu.unlam.tallerweb1.servicios.ServicioLogin;

@Authorized
@Controller("/")
public class HomeControlador {

	private final ServicioEstablecimiento servicioEstablecimiento;
	private final ServicioInsumo servicioInsumo;
	private ServicioLogin servicioLogin;

	@Autowired
	public HomeControlador(ServicioEstablecimiento servicioEstablecimiento, ServicioInsumo servicioInsumo,
			ServicioLogin servicioLogin) {
		this.servicioEstablecimiento = servicioEstablecimiento;
		this.servicioInsumo = servicioInsumo;
		this.servicioLogin = servicioLogin;
	}

	@InitBinder
	public void initBinder(WebDataBinder dataBinder) {
		dataBinder.registerCustomEditor(TipoDeStrategy.class, new StringToTipoDeStrategy());
	}

	@RequestMapping(path = "/", method = RequestMethod.GET)
	public ModelAndView inicio() {
		return new ModelAndView("redirect:/home");
	}

	@RequestMapping(path = "/home", method = RequestMethod.GET)
	public ModelAndView irAHome() {
		// Busco el modelo default y le paso la estrategia default
		ModelMap modelo = this.obtenerModeloDeHome(TipoDeStrategy.COMBINADO);
		return new ModelAndView("home", modelo);
	}

	// Calcular Indice de Prioridad
	// Busca el modelo default / Ejecuta la Prioridad
	@RequestMapping(path = "/home", method = RequestMethod.POST)
	public ModelAndView homeCalcularPrioridad(@ModelAttribute("strategy") TipoDeStrategy strategy) {
		// Busco el modelo default y le paso la prioridad
		ModelMap modelo = this.obtenerModeloDeHome(strategy);
		return new ModelAndView("home", modelo);
	}

	@RequestMapping(path = "/404", method = RequestMethod.GET)
	public ModelAndView irA404() {	
		return new ModelAndView("404");
	}

	// Obtiene los datos por default de la vista home.
	private ModelMap obtenerModeloDeHome(TipoDeStrategy strategy) {
		ModelMap modelo = new ModelMap();

		// Listas para mostrar Establecimientos e insumos
		List<Establecimiento> establecimientos = servicioEstablecimiento.obtenerTodos();
		List<Insumo> insumos = servicioInsumo.obtenerTodos();

		// Populo la lista con prioridades
		List<Establecimiento> establecConPrioridad = strategy.calcularPrioridad(establecimientos);

		// Valores para los widget
		Long cantEstablec = servicioEstablecimiento.cantidadItems(establecimientos);
		Long cantTotalInsumos = servicioInsumo.cantTotalInsumos();

		modelo.put("listaEstablecimientos", establecimientos);
		modelo.put("listaInsumos", insumos);
		modelo.put("cantidadEstablecimientos", cantEstablec);
		modelo.put("cantTotalInsumos", cantTotalInsumos);
		modelo.put("establConPrioridad", establecConPrioridad);
		modelo.put("filtrosActivos", strategy.getTipo());

		return modelo;
	}
}
