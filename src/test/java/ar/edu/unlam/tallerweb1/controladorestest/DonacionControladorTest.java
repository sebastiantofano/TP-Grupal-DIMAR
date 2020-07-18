package ar.edu.unlam.tallerweb1.controladorestest;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import org.junit.Test;
import org.springframework.ui.ModelMap;
import org.springframework.web.servlet.ModelAndView;

import ar.edu.unlam.tallerweb1.controladores.DonacionController;
import ar.edu.unlam.tallerweb1.modelo.Insumo;
import ar.edu.unlam.tallerweb1.servicios.ServicioInsumo;
import ar.edu.unlam.tallerweb1.servicios.ServicioLogin;

public class DonacionControladorTest {
	
	@Test
	public void meDevuelveUnMsgAlCargarElInsumo() throws Exception{
		//Preperacion
		ServicioInsumo servicioInsumo = mock(ServicioInsumo.class);
		ServicioLogin servicioLogin = mock(ServicioLogin.class);
		
		Insumo insumo = new Insumo();
		String cantidad = "40";
		
		when(servicioLogin.verificarSesionActiva()).thenReturn(true);
		when(servicioInsumo.obtenerPorId((1L))).thenReturn(new Insumo());
		doNothing().when(servicioInsumo).actualizarInsumo(insumo, cantidad);
		
		//Ejecucion
		DonacionController DonacionController = new DonacionController(servicioInsumo, servicioLogin);
		ModelAndView modelAndView = DonacionController.enviarDonacion(insumo, cantidad);
		ModelMap modelo = modelAndView.getModelMap();
 
		//Validacion
		assertThat(modelo.get("msg")).isNotNull();	
	}
	
}