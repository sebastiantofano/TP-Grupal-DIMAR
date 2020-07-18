package ar.edu.unlam.tallerweb1.persistencia;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Session;
import org.junit.Test;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;

import ar.edu.unlam.tallerweb1.SpringTest;
import ar.edu.unlam.tallerweb1.modelo.Establecimiento;
import ar.edu.unlam.tallerweb1.modelo.Zona;
import ar.edu.unlam.tallerweb1.repositorios.RepositorioEstablecimiento;
import ar.edu.unlam.tallerweb1.repositorios.RepositorioEstablecimientoImpl;
import ar.edu.unlam.tallerweb1.servicios.ServicioEstablecimientoImpl;

public class EstablecimientosTest extends SpringTest {

	@Test
	@Transactional
	@Rollback
	public void crearEstablecimiento() {
//		Preparacion
		Establecimiento establecimiento = new Establecimiento();
		establecimiento.setNombre("ClinaTEST");

//		Ejecucion
		final Session session = session();
		session().save(establecimiento);
		// Busco en la tabla Establecimiento
		Establecimiento buscado = session.get(Establecimiento.class, establecimiento.getId());

//		Resultado
		assertThat(buscado).isNotNull();
	}

	@Test
	@Transactional
	@Rollback
	public void validarPrioridad_x_Ocupacion() {

//		Preparación
//		RepositorioEstablecimiento repo = new RepositorioEstablecimientoImpl(session().getSessionFactory());		
//		ServicioEstablecimientoImpl servicio = new ServicioEstablecimientoImpl(repo);
//		ServicioDistribucionImpl servicioDistribucion = new ServicioDistribucionImpl();
//		List<Establecimiento> listaEst = new ArrayList<Establecimiento>();
//		Zona zona = new Zona();
//		listaEst.add(new Establecimiento("Establecimiento_TEST", "Ubicacion_TEST", 200, 10, zona, "Responsable_TEST"));
//
////		Ejecucion	
//		List<Establecimiento> litaPrioridad = servicio.servicioDistribucion(TipoDePrioridad.OCUPACION, listaEst);
//		Establecimiento estTesteado = litaPrioridad.get(0);
//
////		Comprobacion
//		assertEquals(5.0, estTesteado.getPrioridad(), 0.0);
	}

	@Test
	@Transactional
	@Rollback
	public void validarPrioridad_x_CapacidadTotal() {

//		Preparación
//		RepositorioEstablecimiento repo = new RepositorioEstablecimientoImpl(session().getSessionFactory());
//		ServicioEstablecimientoImpl servicio = new ServicioEstablecimientoImpl(repo);
//		List<Establecimiento> listaEst = new ArrayList<Establecimiento>();
//		Zona zona = new Zona();
//		listaEst.add(new Establecimiento("Establecimiento_TEST", "Ubicacion_TEST", 500, 10, zona, "Responsable_TEST"));
//		listaEst.add(
//				new Establecimiento("Establecimiento_TEST2", "Ubicacion_TEST2", 200, 10, zona, "Responsable_TEST2"));
//		listaEst.add(
//				new Establecimiento("Establecimiento_TEST3", "Ubicacion_TEST3", 300, 10, zona, "Responsable_TEST3"));
////		Ejecucion	
//		List<Establecimiento> litaPrioridad = servicio.calcularPrioridad(TipoDePrioridad.CAPACIDAD, listaEst);
//		Establecimiento estTesteado = litaPrioridad.get(0);
//
////		Comprobacion
//		assertEquals(50.0, estTesteado.getPrioridad(), 0.0);
	}
}
