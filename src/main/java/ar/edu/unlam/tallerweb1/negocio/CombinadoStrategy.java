package ar.edu.unlam.tallerweb1.negocio;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import ar.edu.unlam.tallerweb1.modelo.Establecimiento;
import ar.edu.unlam.tallerweb1.modelo.Insumo;

//Estrategia: Calcular prioridad por "COMBINADO"
public class CombinadoStrategy implements Strategy {

	@Override
	public List<Establecimiento> calcular(List<Establecimiento> establecimientos) {

		float puntajeTotal = 0;
		for (Establecimiento est : establecimientos) {
			// Factor A: del 0 al 100 que porcentaje de ocupado esta
			float factorA = (est.getOcupacion() * 100) / est.getCapacidad(); 
			// Factor B: puntaje de la zona
			float factorB = est.getZona().getPuntaje(); 
			// Factor C: Entre mas capacidad mas prioritario,10 puntos cada 100 de capacidad.
			float factorC = est.getCapacidad() / 10; 
			// Factor D: Numero random para desempatar.
			float factorD = (float) Math.random(); 
			// Total
			float puntaje = factorA + factorB + factorC + factorD;
			puntajeTotal += puntaje;
			
			est.setPrioridad(puntaje);
		}
		
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
		 /*	Logica de este algoritmo
		  * Se dividen los establecimientos en 5 grupos
		  * grupo 1 recibe el 40 de los insumos > 40% de cada tipo
		  * grupo 2 recibe el 28 de los insumos > 28% de cada tipo
		  * grupo 3 recibe el 17 de los insumos > 17% de cada tipo
		  * grupo 4 recibe el 10 de los insumos > 10% de cada tipo
		  * grupo 5 recibe el 5  de los insumos >  5% de cada tipo
		  * Si al separar en 5 grupos hay resto, los restantes quedan en el grupo 5.
		 */
		
		establecimientos = this.calcular(establecimientos);
		
		// RETORNO
		Map<Establecimiento,List<Insumo>> model = new HashMap<Establecimiento, List<Insumo>>();
		
		// AGRUPO LOS INSUMOS POR TIPO
		Map<String, List<Insumo>> grupoInsumos = insumos.stream().collect(Collectors.groupingBy(w -> w.getTipo()));
		
		// TOTAL INSUMOS
		float totalInsumos = (float)insumos.stream().mapToDouble(o -> o.getCantidad()).sum();
		
		// PARAMETRIZACION X CANTIDAD DE GRUPOS
		int cantGrupos = 5;
		float[] distribucion = {0.40f,0.28f,0.17f,0.10f,0.05f};
		int cantxGrupo = (int) establecimientos.size()/cantGrupos;
		
		// DIVIDO LOS ESTABLECIMIENTOS EN GRUPOS
		int desde = 0;
		int hasta = cantxGrupo;
		float sumatoriaInsumos = 0;
		for (int i = 0; i < cantGrupos; i++) {
			// OBTENGO LA SUBLISTA DE INSUMOS 
			List<Establecimiento> grupo = establecimientos.subList(desde, hasta);
			for(Establecimiento est : grupo) {
				
				// RECORRO LOS TIPOS
				List<Insumo> auxInsumo = new ArrayList<Insumo>();
				for (Map.Entry<String,List<Insumo>> grInsumos : grupoInsumos.entrySet()) {  

					// RECORRO LOS INSUMOS
					List<Insumo> insumosDeTipo = grInsumos.getValue();
					for(Insumo insumo : insumosDeTipo) {
						if(sumatoriaInsumos < totalInsumos) {
							// COPIO EL INSUMO
							Insumo clon = new Insumo();
							clon.setNombre(insumo.getNombre());
							clon.setId(insumo.getId());
							int cantidad = Math.round(Math.round((insumo.getCantidad()*distribucion[i])/grupo.size()));
							sumatoriaInsumos += cantidad;
							if(sumatoriaInsumos > totalInsumos) {
								cantidad -= sumatoriaInsumos - totalInsumos;
							}
							clon.setCantidad(cantidad);
							auxInsumo.add(clon);	
						}
					}			
				}
				// ASOCIO LOS INSUMOS A UN ESTABLECIMIENTO
				model.put(est, auxInsumo);
			}

			
			// PROXIMA RONDA
			desde = hasta;
			hasta = hasta + cantxGrupo;
			if(hasta > establecimientos.size()-1) {
				hasta = establecimientos.size();
			}
		}
		
		return model;
	}
}
