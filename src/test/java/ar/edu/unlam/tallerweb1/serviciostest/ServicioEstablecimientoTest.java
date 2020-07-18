package ar.edu.unlam.tallerweb1.serviciostest;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

import ar.edu.unlam.tallerweb1.SpringTest;
import ar.edu.unlam.tallerweb1.modelo.Establecimiento;
import ar.edu.unlam.tallerweb1.repositorios.RepositorioEstablecimiento;
import ar.edu.unlam.tallerweb1.servicios.ServicioEstablecimientoImpl;

public class ServicioEstablecimientoTest extends SpringTest {
	
	@Test
	public void elServicioEstablecimientoMayorOcupacionMeDevuelveUnEstablecimientoSolo() {
		//Preparacion
		RepositorioEstablecimiento servicioEstablecimientoDao = mock(RepositorioEstablecimiento.class);
		
		List<Establecimiento> lista = new ArrayList<Establecimiento>();
		Establecimiento establec1 = new Establecimiento();
		Establecimiento establec2 = new Establecimiento();
		establec1.setOcupacion(1000);
		establec2.setOcupacion(2000);
		lista.add(establec1);
		lista.add(establec2);
		
		//Ejecucion
		ServicioEstablecimientoImpl servicioEstablecimiento = new ServicioEstablecimientoImpl(servicioEstablecimientoDao);
		when(servicioEstablecimientoDao.getAll()).thenReturn(lista);
		Establecimiento unicoEstablecimientoDevuelto = servicioEstablecimiento.establecimientoMayorOcupacion();
		unicoEstablecimientoDevuelto.setNombre("Establecimiento con mayor capacidad");
		
		//Validacion
		assertThat(unicoEstablecimientoDevuelto).isNotNull();
		assertThat(unicoEstablecimientoDevuelto).isInstanceOf(Establecimiento.class);
		assertTrue(unicoEstablecimientoDevuelto.getOcupacion().equals(2000));
	}

}
