package ar.edu.unlam.tallerweb1.comun.enums;

import java.util.List;
import java.util.Map;

import ar.edu.unlam.tallerweb1.modelo.Establecimiento;
import ar.edu.unlam.tallerweb1.modelo.Insumo;
import ar.edu.unlam.tallerweb1.negocio.CapacidadStrategy;
import ar.edu.unlam.tallerweb1.negocio.CombinadoStrategy;
import ar.edu.unlam.tallerweb1.negocio.EquitativoStrategy;
import ar.edu.unlam.tallerweb1.negocio.OcupacionStrategy;
import ar.edu.unlam.tallerweb1.negocio.Strategy;
import ar.edu.unlam.tallerweb1.negocio.ZonaStrategy;

// Contiene el "Contexto del strategy"
public enum TipoDeStrategy {
	COMBINADO(1, "Combinado", new CombinadoStrategy()), OCUPACION(2, "Ocupacion", new OcupacionStrategy()),
	CAPACIDAD(3, "Capacidad", new CapacidadStrategy()), ZONA(4, "Zona", new ZonaStrategy()),
	EQUITATIVO(5, "Equitativo", new EquitativoStrategy());

	private int id;
	private String tipo;
	private Strategy strategy;

	// Constructor privado del enum
	private TipoDeStrategy(int id, String tipo, Strategy strategy) {
		this.id = id;
		this.tipo = tipo;
		this.strategy = strategy;
	}

	// Los enum solo tienen getters
	public String getTipo() {
		return this.tipo;
	}

	public int getId() {
		return id;
	}

	public List<Establecimiento> calcularPrioridad(List<Establecimiento> establecimientos) {
		return strategy.calcular(establecimientos);
	}

	public Map<Establecimiento, List<Insumo>> distribuirInsumos(List<Establecimiento> establecimientos,
			List<Insumo> insumos) {
		return strategy.distribuir(establecimientos, insumos);
	}
}
