package ar.edu.unlam.tallerweb1.controladorestest;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Matchers.isA;
import static org.mockito.Matchers.isNull;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import javax.inject.Inject;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.servlet.ModelAndView;

import ar.edu.unlam.tallerweb1.controladores.ResponsableControlador;
import ar.edu.unlam.tallerweb1.servicios.ServicioEstablecimiento;
import ar.edu.unlam.tallerweb1.servicios.ServicioLogin;
import ar.edu.unlam.tallerweb1.servicios.ServicioResponsable;

public class ResponsableControladorTest {


	
	@Test
	public void meLlevaALaVistaDeResponsasbles(){
		//Preperacion
		ServicioLogin servicioLogin = mock(ServicioLogin.class);
		ServicioResponsable servicioResponsable = mock(ServicioResponsable.class);
		ServicioEstablecimiento servicioEstablecimiento = mock(ServicioEstablecimiento.class);
		//Ejecucion
		
		ResponsableControlador controladorResponsable = new ResponsableControlador(servicioResponsable, servicioEstablecimiento,servicioLogin);
		when(servicioLogin.verificarSesionActiva()).thenReturn(true);
		ModelAndView modelAndView = controladorResponsable.responsables();
		

		
		//Validacion
		assertThat(modelAndView.getViewName()).isEqualTo("responsables");	
	}
	
	@Test
	public void meAgregaElModeloAlaVistaResponsable(){
		//Preperacion
		ServicioLogin servicioLogin = mock(ServicioLogin.class);
		ServicioResponsable servicioResponsable = mock(ServicioResponsable.class);
		ServicioEstablecimiento servicioEstablecimiento = mock(ServicioEstablecimiento.class);

		
		//Ejecucion
		ResponsableControlador controladorResponsable = new ResponsableControlador(servicioResponsable, servicioEstablecimiento,servicioLogin);
		when(servicioLogin.verificarSesionActiva()).thenReturn(true);
		ModelAndView modelAndView = controladorResponsable.responsables();
		
		//Validacion
		assertThat(modelAndView.getModel()).isNotNull();	
	}
}
