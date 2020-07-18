package ar.edu.unlam.tallerweb1.comun.enums;

import java.util.HashMap;
import java.util.Map;

@SuppressWarnings("unchecked")
public enum TipoDeEstadoDistribucion {

	PENDIENTE_REVISION(1, "Pendiente de Revision"), PENDIENTE_COORDINACION(2, "Pendiente de Coordinacion"),
	ENTREGA_COORDINADA(3, "Entrega Coordinada"), ENTREGADO(4, "Entregado"),
	PROBLEMA_REVISION(5, "Problema en Revision"), PROBLEMA_COORDINACION(6, "Problema en Coordinacion"),
	PROBLEMA_ENTREGA(7, "Problema en Entrega");

	private int id;
	private String descripcion;

	@SuppressWarnings("rawtypes")
	private static Map map = new HashMap<>();

	private TipoDeEstadoDistribucion(int id, String descripcion) {
		this.id = id;
		this.descripcion = descripcion;
	}

	static {
		for (TipoDeEstadoDistribucion type : TipoDeEstadoDistribucion.values()) {
			map.put(type.id, type);
		}
	}

	// Los enum solo tienen getters
	public String getDescripcion() {
		return this.descripcion;
	}

	public int getId() {
		return this.id;
	}

	public static TipoDeEstadoDistribucion valueOf(int estado) {
		return (TipoDeEstadoDistribucion) map.get(estado);
	}
}
