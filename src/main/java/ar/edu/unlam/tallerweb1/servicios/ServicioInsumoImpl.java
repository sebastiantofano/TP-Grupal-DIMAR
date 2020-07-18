package ar.edu.unlam.tallerweb1.servicios;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import ar.edu.unlam.tallerweb1.modelo.Distribucion;
import ar.edu.unlam.tallerweb1.modelo.Establecimiento;
import ar.edu.unlam.tallerweb1.modelo.Insumo;
import ar.edu.unlam.tallerweb1.repositorios.RepositorioEstablecimiento;
import ar.edu.unlam.tallerweb1.repositorios.RepositorioInsumo;

@Service("servicioInsumo")
@Transactional
public class ServicioInsumoImpl implements ServicioInsumo {

	private RepositorioInsumo servicioInsumoDao;
	private RepositorioEstablecimiento servicioEstablecimientoDao;

	@Autowired
	public ServicioInsumoImpl(RepositorioInsumo servicioInsumoDao,
			RepositorioEstablecimiento servicioEstablecimientoDao) {
		this.servicioInsumoDao = servicioInsumoDao;
		this.servicioEstablecimientoDao = servicioEstablecimientoDao;
	}

	@Override
	public List<Insumo> obtenerTodos() {
		return servicioInsumoDao.getAll();
	}

	// GetById
	@Override
	public Insumo obtenerPorId(Long id) {
		return servicioInsumoDao.getById(id);
	}
	
	// Retorna la cantidad de insumos disponibles, realiza un SUM del campo
	// 'cantidad'
	@Override
	public Long cantTotalInsumos() {
		return servicioInsumoDao.cantTotalInsumos();
	}

	@Override
	public Long insumosSobrantes() {
		Long totalEstablec = (long) servicioEstablecimientoDao.getAll().size();
		List<Insumo> listaInsumos = servicioInsumoDao.getAll();

		Long insumosSobrantes = 0L;
		for (Insumo itemInsumo : listaInsumos) {
			insumosSobrantes = insumosSobrantes + (itemInsumo.getCantidad() % totalEstablec);
		}
		return insumosSobrantes;
	}

	@Override
	public void actualizarInsumo(Insumo insumoAModificar, String cantidad) throws Exception {
		Integer cantidadDonada = Integer.parseInt(cantidad);
		if(cantidadDonada > 0) {
			Integer cantActual = insumoAModificar.getCantidad();
			insumoAModificar.setCantidad(cantActual + cantidadDonada);
			servicioInsumoDao.update(insumoAModificar);	
		}else{
			throw new Exception();
		}
	
	}

	@Override
	public Map<Insumo, Integer> recibirDonaciones() {
		List<Insumo> insumos = servicioInsumoDao.getAll();
		Map<Insumo,Integer> donacionesMap = new HashMap<Insumo, Integer>();
		
		for(Insumo insumo : insumos) {
			Integer donacionRandom = new Random().nextInt(101);
			donacionesMap.put(insumo, donacionRandom);
		}
		return donacionesMap;
	}

}
