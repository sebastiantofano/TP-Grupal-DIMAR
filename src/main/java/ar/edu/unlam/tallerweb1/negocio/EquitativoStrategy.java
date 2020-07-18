package ar.edu.unlam.tallerweb1.negocio;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.util.StringUtils;

import ar.edu.unlam.tallerweb1.modelo.Establecimiento;
import ar.edu.unlam.tallerweb1.modelo.Insumo;

public class EquitativoStrategy implements Strategy {

	@Override
	public List<Establecimiento> calcular(List<Establecimiento> establecimientos) {
		for (Establecimiento itemEstablec : establecimientos) {
			if(StringUtils.isEmpty(itemEstablec.getPrioridad())) {
				itemEstablec.setPrioridad(1F);
			}	
		}
		return establecimientos;
	}

	@Override
	public Map<Establecimiento, List<Insumo>> distribuir(List<Establecimiento> establecimientos, List<Insumo> insumos) {

		Map<Establecimiento, List<Insumo>> asignacion = new HashMap<Establecimiento, List<Insumo>>();

		establecimientos = this.calcular(establecimientos);

		// Verifico si va a ser una distribución equitativa por default o con prioridad de asignación.
		Boolean distribucionDefault = establecimientos.stream().filter(o -> o.getPrioridad().equals(2F)).findFirst().isPresent() ? false : true;
		
		
		// Cantidad de insumos totales
		Integer totalInsumos = 0;
		for (Insumo item : insumos) {
			totalInsumos = totalInsumos + item.getCantidad();
		}	
		
		// Cantidad de establecimientos totales
		Integer cantidadEstablec = establecimientos.size();

		// Asignación de Insumos a Establecimientos
		for (Establecimiento itemEstablec : establecimientos) {
			// Lista que será asignada al establecimiento	
			List<Insumo> insumosAsignados = new ArrayList<Insumo>();

			for (Insumo itemInsumo : insumos) {
				Insumo insumoAsignado = new Insumo();
				insumoAsignado.setNombre(itemInsumo.getNombre());
				insumoAsignado.setTipo(itemInsumo.getTipo());
				insumoAsignado.setId(itemInsumo.getId());

				insumoAsignado.setCantidad((int) itemInsumo.getCantidad() / cantidadEstablec);
	
				//Asignación de insumos restantes al establecimiento con maxima cantidad de infectados solo si es la distribucion equitativa default
				Establecimiento establecMaxPrioDefault = establecimientos.stream().max(Comparator.comparing(Establecimiento::getOcupacion)).get();
				if(itemEstablec.getId() == establecMaxPrioDefault.getId() && distribucionDefault == true) {
					Integer InsumoASumar = insumoAsignado.getCantidad();
					int InsumoRestante = itemInsumo.getCantidad() % cantidadEstablec;		
					insumoAsignado.setCantidad(InsumoASumar+InsumoRestante);	
				}
				
				// Si la distribucion equitativa tiene prioridad sobre uno de los establecimientos es porque le seteo una prioridad de 2F
				if(itemEstablec.getPrioridad() == 2F) {
					Integer InsumoASumar = insumoAsignado.getCantidad();
					int InsumoRestante = itemInsumo.getCantidad() % cantidadEstablec;		
					insumoAsignado.setCantidad(InsumoASumar+InsumoRestante);
					
				}	
			
				// Agrego insumo a la lista de insumos
				insumosAsignados.add(insumoAsignado);
			}

			// Agrego la lista de Insumos al Establecimiento
			asignacion.put(itemEstablec, insumosAsignados);
		}
		return asignacion;
	}
	
	public Map<Establecimiento, List<Insumo>> cambiarEstablecInsumosRestantes(List<Establecimiento> establecimientos, List<Insumo> insumos, Establecimiento establecimientoPrioridad){
		
		// Si se requiere un cambio de establecimiento modifico su prioridad
		for(Establecimiento establecimiento : establecimientos) {
			if(establecimiento.getId() == establecimientoPrioridad.getId()) establecimiento.setPrioridad(2F);
		}
			return this.distribuir(establecimientos, insumos);	
	}

}
