package ar.edu.unlam.tallerweb1.controladorestest;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;
import org.springframework.web.servlet.ModelAndView;

import ar.edu.unlam.tallerweb1.controladores.MapaControlador;
import ar.edu.unlam.tallerweb1.modelo.Establecimiento;
import ar.edu.unlam.tallerweb1.modelo.Zona;
import ar.edu.unlam.tallerweb1.servicios.ServicioEstablecimiento;
import ar.edu.unlam.tallerweb1.servicios.ServicioLogin;

public class MapaControladorTest {

	@Test
	public void voidirAMapaRedireccionaALaVistaMapa() {
		// Preparacion
		ServicioLogin servicioLogin = mock(ServicioLogin.class);
		ServicioEstablecimiento servicioEstablecimiento = mock(ServicioEstablecimiento.class);

		MapaControlador controlador = new MapaControlador(servicioEstablecimiento, servicioLogin);
		when(servicioLogin.verificarSesionActiva()).thenReturn(true);
		
		// Ejecucion
		ModelAndView modelAndView = controlador.irAMapa();

		// Validacion
		assertThat(modelAndView.getViewName()).contains("mapa");
	}
	
	
	@Test
	public void getDataRetorneUnString() {
		// Preperacion
		ServicioLogin servicioLogin = mock(ServicioLogin.class);
		ServicioEstablecimiento servicioEstablecimiento = mock(ServicioEstablecimiento.class);
		
		// simulacion de objetos
		List<Establecimiento> establecimientos = new ArrayList<Establecimiento>();

		for (int i = 1; i < 6; i++) {
			Establecimiento est = new Establecimiento();
			est.setCapacidad(10 * i);
			est.setId(i);
			est.setNombre("prueba" + i);
			est.setOcupacion(10 * i);
			Zona zn = new Zona();
			zn.setNombre("zona" + i);
			zn.setPuntaje(10 * i);
			est.setZona(zn);
			establecimientos.add(est);
		}

		// condiciones de carga
		MapaControlador controlador = new MapaControlador(servicioEstablecimiento, servicioLogin);
		when(servicioEstablecimiento.obtenerTodos()).thenReturn(establecimientos);
		when(servicioLogin.verificarSesionActiva()).thenReturn(true);
		
		// Ejecucion
		List<Establecimiento> subEstablecimientos = controlador.obtenerJSONMapa();

		// Validacion
		assertThat(establecimientos).isSameAs(subEstablecimientos);
	}
	
	
}