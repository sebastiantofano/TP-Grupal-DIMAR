package ar.edu.unlam.tallerweb1.negocio;

import java.util.List;
import java.util.Map;

import ar.edu.unlam.tallerweb1.modelo.Establecimiento;
import ar.edu.unlam.tallerweb1.modelo.Insumo;
// Patr�n de dise�o "Strategy" para calcular: Prioridad de Riesgo
public interface Strategy {
	
	//Tendr� diferentes implementaciones de acuerdo a como se calcule la prioridad de riesgo.	
	List<Establecimiento> calcular(List<Establecimiento> establecimientos);
	
	Map<Establecimiento, List<Insumo>> distribuir(List<Establecimiento> establecimientos, List<Insumo> insumos);
	
}
