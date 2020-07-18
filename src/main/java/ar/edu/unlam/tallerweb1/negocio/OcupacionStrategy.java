package ar.edu.unlam.tallerweb1.negocio;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Predicate;
import java.util.stream.Collectors;

import ar.edu.unlam.tallerweb1.modelo.Establecimiento;
import ar.edu.unlam.tallerweb1.modelo.Insumo;

//Estrategia: Calcular prioridad por "OCUPACIÓN"
public class OcupacionStrategy implements Strategy {
	
	// Convierte los porcentajes en prioridad
	@Override
	public List<Establecimiento> calcular(List<Establecimiento> establecimientos) {
	
		// ¿Acá es donde sucede la referencia de memoria?		
		establecimientos = this.calcularPorcentajeDeDistribucion(establecimientos);
		
		// Ordeno la lista por prioridad		
		Collections.sort(establecimientos,(a,b) -> {
			return (int) (b.getPrioridad() - a.getPrioridad());
		});

		for (int i = 0; i < establecimientos.size(); i++) {
			establecimientos.get(i).setPrioridad((float)(i+1));
		}
		return establecimientos;
	}
	
	@Override
	public Map<Establecimiento, List<Insumo>> distribuir(List<Establecimiento> establecimientos, List<Insumo> insumos) {
		
		establecimientos = this.calcularPorcentajeDeDistribucion(establecimientos);
		Map<Establecimiento,List<Insumo>> distribucionesMap =  new HashMap<Establecimiento, List<Insumo>>();

		//Cantidad total de insumos
		Integer totalInsumos = 0;
		for(Insumo item : insumos) {
			totalInsumos = totalInsumos + item.getCantidad();
		}
		
		// Calculo de Rangos		
		Float promMaximo = establecimientos.stream().max(Comparator.comparing(Establecimiento::getPrioridad)).get().getPrioridad();
		Float promMitad = promMaximo/2;
		Float promCuarto = promMitad/2;
		
		//Contador de establecimientos para distribuir en rangos
		Integer contadorEstAlta = 0;
		Integer contadorEstMedia = 0;
		Integer contadorEstBaja = 0;
		for(Establecimiento itemEstablec : establecimientos) {
			 if(itemEstablec.getPrioridad() > promMitad) {
				contadorEstAlta++;	
			}else if(itemEstablec.getPrioridad() > promCuarto) {
				contadorEstMedia++;
			}else {
				contadorEstBaja++;		
			}		
		}	
		
		//	Asignación de Insumo por insumo a Establecimientos
		for(Establecimiento itemEstablec : establecimientos) {
			// Lista que será asignada al establecimiento			
			List<Insumo> insumosAsignados = new ArrayList<Insumo>();
			
			for(Insumo itemInsumo : insumos) {
				Insumo insumoTemp = new Insumo();
				insumoTemp.setNombre(itemInsumo.getNombre());
				insumoTemp.setTipo(itemInsumo.getTipo());
				insumoTemp.setId(itemInsumo.getId());
						
				if(itemEstablec.getPrioridad() > promMitad) {
					insumoTemp.setCantidad((int) (itemInsumo.getCantidad()*0.6) / contadorEstAlta);
				}else if(itemEstablec.getPrioridad() > promCuarto) {				
					insumoTemp.setCantidad((int) (itemInsumo.getCantidad()*0.3) / contadorEstMedia);
				}else {					
					insumoTemp.setCantidad((int) (itemInsumo.getCantidad()*0.1) / contadorEstBaja);			
				}
						
				//Le asigno al establecimiento con mayor prioridad los insumos restantes
				Integer cantidadEstablec = establecimientos.size();	
				Integer InsumoASumar = insumoTemp.getCantidad();
				int sumaInsumoRestante = itemInsumo.getCantidad() % cantidadEstablec;		
				Establecimiento establecimientoMaxPrioridad = 						establecimientos.stream().max(Comparator.comparing(Establecimiento::getPrioridad)).get();
				if(itemEstablec.equals(establecimientoMaxPrioridad)) {
					insumoTemp.setCantidad(InsumoASumar+sumaInsumoRestante);
				}
				
				//Agrego insumo a la lista de insumos				
				insumosAsignados.add(insumoTemp);
			}
			
			//Agrego la lista de insumos correspondiente distribuidos al establecimiento
			distribucionesMap.put(itemEstablec, insumosAsignados);
		}
		
		// Convierte porcentaje a prioridad - PREGUNTAR PORQUE FUNCIONA, ¿AMBITO DE VARIABLES?
		this.calcular(establecimientos);
		
		/*Otra solución para para match de la prioridad con el porcentaje*/
//		for (Establecimiento est : establecimientosPtos) {
//			for (Map.Entry<Establecimiento,List<Insumo>> asg : distribucionesMap.entrySet()) {  
//				if(asg.getKey().getId() == est.getId()) {
//					asg.getKey().setPrioridad(est.getPrioridad());
//				}
//			}
//		}
		
		return distribucionesMap;
	}
	
	public List<Establecimiento> calcularPorcentajeDeDistribucion(List<Establecimiento> establecimientos) {
		// Calculo el total de Ocupacion 		
		Integer totalOcupacion = 0;
		for(Establecimiento itemEstablec : establecimientos) {
			totalOcupacion = totalOcupacion + itemEstablec.getOcupacion();
		}
		
		// Asigno su porcentaje de ocupacion correspondiente		
		for (Establecimiento itemEstablec : establecimientos) {
			Float prioridad = (((float) itemEstablec.getOcupacion() * 100) / (float) totalOcupacion);
			itemEstablec.setPrioridad(prioridad);
		}
		
		return establecimientos;
	}
	
	
}
