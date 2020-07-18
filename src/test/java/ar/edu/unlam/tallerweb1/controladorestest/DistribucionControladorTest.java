package ar.edu.unlam.tallerweb1.controladorestest;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;
import org.springframework.ui.ModelMap;
import org.springframework.web.servlet.ModelAndView;

import ar.edu.unlam.tallerweb1.comun.enums.TipoDeStrategy;
import ar.edu.unlam.tallerweb1.controladores.DistribucionControlador;
import ar.edu.unlam.tallerweb1.modelo.Establecimiento;
import ar.edu.unlam.tallerweb1.modelo.Insumo;
import ar.edu.unlam.tallerweb1.modelo.Zona;
import ar.edu.unlam.tallerweb1.servicios.ServicioEstablecimiento;
import ar.edu.unlam.tallerweb1.servicios.ServicioInsumo;
import ar.edu.unlam.tallerweb1.servicios.ServicioLogin;
import ar.edu.unlam.tallerweb1.servicios.ServicioDistribucion;
import ar.edu.unlam.tallerweb1.servicios.ServicioDistribucionDetalle;

@RunWith(PowerMockRunner.class)
@PrepareForTest(fullyQualifiedNames = "ar.edu.unlam.tallerweb1.*")
public class DistribucionControladorTest{

	@Test
	public void meRedireccionaALaPaginaDeDistribucion() {
		// Preperacion
		ServicioLogin servicioLogin = mock(ServicioLogin.class);
		ServicioInsumo servicioInsumo = mock(ServicioInsumo.class);
		ServicioEstablecimiento servicioEstablecimiento = mock(ServicioEstablecimiento.class);
		ServicioDistribucion servicioDistribucion = mock(ServicioDistribucion.class);
		ServicioDistribucionDetalle servicioDistribucionDetalle = mock(ServicioDistribucionDetalle.class);

		DistribucionControlador distribucionControlador = new DistribucionControlador(servicioEstablecimiento,
				servicioInsumo, servicioDistribucion, servicioDistribucionDetalle,servicioLogin);
		when(servicioLogin.verificarSesionActiva()).thenReturn(true);
		
		// Ejecucion
		ModelAndView modelAndView = distribucionControlador.irADistribucion();

		// Validacion
		assertThat(modelAndView.getViewName()).contains("distribucion");
	}

	@Test
	public void distribuyeLosInsumosPorStrategy() {
		// Preperacion
		ServicioLogin servicioLogin = mock(ServicioLogin.class);
		ServicioInsumo servicioInsumo = mock(ServicioInsumo.class);
		ServicioEstablecimiento servicioEstablecimiento = mock(ServicioEstablecimiento.class);
		ServicioDistribucion servicioDistribucion = mock(ServicioDistribucion.class);
		ServicioDistribucionDetalle servicioDistribucionDetalle = mock(ServicioDistribucionDetalle.class);
		DistribucionControlador distribucionControlador = new DistribucionControlador(servicioEstablecimiento,
				servicioInsumo, servicioDistribucion, servicioDistribucionDetalle,servicioLogin);
		TipoDeStrategy str = mock(TipoDeStrategy.class);
		
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

		List<Insumo> insumos = new ArrayList<Insumo>();
		Insumo ins = new Insumo();
		ins.setNombre("test");
		ins.setCantidad(100);
		ins.setTipo("tipo");
		insumos.add(ins);

		Map<Establecimiento, List<Insumo>> distribucion = new HashMap<Establecimiento, List<Insumo>>();

		distribucion.put(establecimientos.get(0), insumos);
		distribucion.put(establecimientos.get(1), insumos);

		TipoDeStrategy strategy = TipoDeStrategy.CAPACIDAD;

		// condiciones de carga
		when(servicioEstablecimiento.obtenerTodos()).thenReturn(establecimientos);
		when(servicioInsumo.obtenerTodos()).thenReturn(insumos);
		when(servicioInsumo.insumosSobrantes()).thenReturn((long) 1);
		when(servicioEstablecimiento.establecimientoMayorOcupacion()).thenReturn(establecimientos.get(0));
		when(str.distribuirInsumos(establecimientos, insumos)).thenReturn(distribucion);
		when(servicioLogin.verificarSesionActiva()).thenReturn(true);
		
		// Ejecucion
		ModelAndView modelAndView = distribucionControlador.calcularDistribucion(str);
		ModelMap model = modelAndView.getModelMap();

		System.out.println(modelAndView);

		// Validacion
		assertThat(model.get("MapaDistribuido")).isSameAs(distribucion);
		assertThat(model.get("establecimiento")).isNotNull();
		assertThat(model.get("insumosSobrantes")).isNotNull();
		assertThat(model.get("establecMayorOcupacion")).isNotNull();

	}
}
