package ar.edu.unlam.tallerweb1.controladorestest;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;
import org.springframework.ui.ModelMap;
import org.springframework.web.servlet.ModelAndView;

import ar.edu.unlam.tallerweb1.comun.enums.TipoDeStrategy;
import ar.edu.unlam.tallerweb1.controladores.HomeControlador;
import ar.edu.unlam.tallerweb1.modelo.Establecimiento;
import ar.edu.unlam.tallerweb1.servicios.ServicioEstablecimiento;
import ar.edu.unlam.tallerweb1.servicios.ServicioInsumo;
import ar.edu.unlam.tallerweb1.servicios.ServicioLogin;

public class HomeControladorTest {

	@Test
	public void inicioMeRedireccionaALaVistaHome() {
		// Preparacion
		ServicioLogin servicioLogin = mock(ServicioLogin.class);
		ServicioInsumo servicioInsumo = mock(ServicioInsumo.class);
		ServicioEstablecimiento servicioEstablecimiento = mock(ServicioEstablecimiento.class);

		HomeControlador homeControlador = new HomeControlador(servicioEstablecimiento, servicioInsumo,servicioLogin);
		when(servicioLogin.verificarSesionActiva()).thenReturn(true);
		
		// Ejecucion
		ModelAndView modelAndView = homeControlador.inicio();

		// Validacion
		assertThat(modelAndView.getViewName()).contains("home");
	}

	@Test
	public void homeMeLlevaALaVistaHome() {
		// Preparacion
		ServicioLogin servicioLogin = mock(ServicioLogin.class);
		ServicioInsumo servicioInsumo = mock(ServicioInsumo.class);
		ServicioEstablecimiento servicioEstablecimiento = mock(ServicioEstablecimiento.class);

		HomeControlador homeControlador = new HomeControlador(servicioEstablecimiento, servicioInsumo,servicioLogin);
		when(servicioLogin.verificarSesionActiva()).thenReturn(true);
		
		// Ejecucion
		ModelAndView modelAndView = homeControlador.irAHome();

		// Validacion
		assertThat(modelAndView.getViewName()).contains("home");
	}

	@Test
	public void homeMeLlenaElModeloCorrectamente() {
		// Preparacion
		ServicioLogin servicioLogin = mock(ServicioLogin.class);
		ServicioInsumo servicioInsumo = mock(ServicioInsumo.class);
		ServicioEstablecimiento servicioEstablecimiento = mock(ServicioEstablecimiento.class);

		when(servicioLogin.verificarSesionActiva()).thenReturn(true);
		List<Establecimiento> establecimientos = new ArrayList<>();

		when(servicioEstablecimiento.obtenerTodos()).thenReturn(establecimientos);
		
		HomeControlador homeControlador = new HomeControlador(servicioEstablecimiento, servicioInsumo,servicioLogin);

		// Ejecucion
		ModelAndView modelAndView = homeControlador.irAHome();
		ModelMap modelMap = modelAndView.getModelMap();

		// Validacion
		assertTrue(modelMap.containsKey("listaEstablecimientos"));

		assertThat(modelMap.get("listaEstablecimientos")).isSameAs(establecimientos);

		assertTrue(modelMap.containsKey("listaInsumos"));
		assertTrue(modelMap.containsKey("cantidadEstablecimientos"));
		assertTrue(modelMap.containsKey("cantTotalInsumos"));
		assertTrue(modelMap.containsKey("establConPrioridad"));
		assertTrue(modelMap.containsKey("filtrosActivos"));
	}

	@Test
	public void calcularPrioridadPorStrategyDevuelveFiltroActivo() {
		// Preparacion
		ServicioLogin servicioLogin = mock(ServicioLogin.class);
		ServicioInsumo servicioInsumo = mock(ServicioInsumo.class);
		ServicioEstablecimiento servicioEstablecimiento = mock(ServicioEstablecimiento.class);

		HomeControlador homeControlador = new HomeControlador(servicioEstablecimiento, servicioInsumo,servicioLogin);
		when(servicioLogin.verificarSesionActiva()).thenReturn(true);
		
		// Ejecucion
		ModelAndView modelAndView = homeControlador.homeCalcularPrioridad(TipoDeStrategy.ZONA);
		ModelMap modelMap = modelAndView.getModelMap();

		// Validacion
		assertTrue("Zona".equalsIgnoreCase((String) modelMap.get("filtrosActivos")));
	}
}
