package ar.edu.unlam.tallerweb1.negocio;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import ar.edu.unlam.tallerweb1.comun.modelos.negocio.GrupoDeDistribucion;
import ar.edu.unlam.tallerweb1.modelo.Establecimiento;
import ar.edu.unlam.tallerweb1.modelo.Insumo;

// Estrategia: Calcular prioridad por "CAPACIDAD"
public class CapacidadStrategy implements Strategy {

	private List<GrupoDeDistribucion> grupos;

	public CapacidadStrategy() {
		this.grupos = new ArrayList<GrupoDeDistribucion>();
	}

	@Override
	public List<Establecimiento> calcular(List<Establecimiento> establecimientos) {

		this.establecerPrioridad(establecimientos);

		Integer prioridad = 1;

		for (Establecimiento item : establecimientos) {
			item.setPrioridad((float) prioridad);
			prioridad++;
		}

		return establecimientos;
	}

	@Override
	public Map<Establecimiento, List<Insumo>> distribuir(List<Establecimiento> establecimientos, List<Insumo> insumos) {
		Map<Establecimiento, List<Insumo>> distribucion = new HashMap<Establecimiento, List<Insumo>>();

		this.establecerPrioridad(establecimientos);

		this.definirGrupos(establecimientos);

		for (Establecimiento establecimiento : establecimientos) {
			List<Insumo> insumosAsignados = new ArrayList<Insumo>();

			for (Insumo itemInsumo : insumos) {
				Insumo insumoTemporal = new Insumo();
				insumoTemporal.setNombre(itemInsumo.getNombre());
				insumoTemporal.setTipo(itemInsumo.getTipo());
				insumoTemporal.setId(itemInsumo.getId());

				// Obtengo Grupo
				GrupoDeDistribucion grupo = this.obtenerGrupo(establecimiento);

				// Seteo la cantidad de insumos en base al grupo
				insumoTemporal.setCantidad(
						(int) (itemInsumo.getCantidad() * grupo.getPorcentajeADistribuir()) / grupo.getCantidad());

				// Agrego insumo a la lista de insumos
				insumosAsignados.add(insumoTemporal);
			}

			// Agrego la lista de insumos correspondiente distribuidos al establecimiento
			distribucion.put(establecimiento, insumosAsignados);
		}

		this.calcular(establecimientos);

		return distribucion;
	}

	// Metodo para definir los grupos. Al ser la capacidad un numero muy variable,
	// intente hacer este algoritmo que a traves de multiplos de 2, cree los grupos
	private void definirGrupos(List<Establecimiento> establecimientos) {
		Integer capacidadTotal = establecimientos.stream().map(Establecimiento::getCapacidad)
				.mapToInt(Integer::intValue).sum();

		Integer divisor = 2;

		boolean noTodosTienenGrupo = true;
		boolean noTodosLosGruposTienenEstablecimientos = true;

		while (noTodosTienenGrupo || noTodosLosGruposTienenEstablecimientos) {
			// Limpio los grupos
			this.grupos.clear();

			// Reinicio variables
			Integer minimo = 0;
			Integer maximo = capacidadTotal;

			// Siempre busco 4 grupos
			for (int i = 1; i <= 4; i++) {
				Integer max = 0;

				// Por lo general, cuando el divisor es 32, si utilizo el divisor 16 pero le
				// sumo la mitad, seria el fin de la busqueda de grupos.
				if (divisor == 32) {
					Integer auxiliar = (maximo / 16);
					max = (auxiliar + (auxiliar / 2)) * i;
				} else {
					max = (maximo / divisor) * i;
				}

				this.grupos.add(new GrupoDeDistribucion(minimo, max, ((float) i / (float) 10)));

				minimo = max + 1;
			}

			noTodosTienenGrupo = false;

			for (Establecimiento establecimiento : establecimientos) {
				boolean tieneGrupo = false;

				for (GrupoDeDistribucion grupo : this.grupos) {
					if (this.perteneceAlGrupo(establecimiento, grupo)) {
						tieneGrupo = true;
						grupo.setCantidad(grupo.getCantidad() + 1);
						break;
					}
				}

				if (!tieneGrupo) {
					noTodosTienenGrupo = true;
					break;
				}
			}

			// Busco algun grupo que tenga cantidad igual 0
			noTodosLosGruposTienenEstablecimientos = this.grupos.stream().anyMatch(g -> g.getCantidad() == 0);

			// Incremento el divisor siempre por 2, para ir achicando los grupos.
			divisor = divisor * 2;
		}
	}

	// Metodo para establecer la prioridad.
	private void establecerPrioridad(List<Establecimiento> establecimientos) {
		for (Establecimiento establecimiento : establecimientos) {
			establecimiento.setPrioridad((float) establecimiento.getCapacidad());
		}

		// Creo un comparador.
		Comparator comparator = Comparator.comparing(Establecimiento::getPrioridad);

		// Orderno la lista.
		Collections.sort(establecimientos, comparator.reversed());
	}

	// Trabajo con los Puntajes Minimos y maximos de la Tupla para poder ver si el
	// establecimiento pertenece al grupo
	private boolean perteneceAlGrupo(Establecimiento establecimiento, GrupoDeDistribucion grupo) {
		return establecimiento.getPrioridad() >= grupo.getPuntajeMinimo()
				&& establecimiento.getPrioridad() <= grupo.getPuntajeMaximo();
	}

	// Metodo para obtener en que grupo esta el establecimiento
	private GrupoDeDistribucion obtenerGrupo(Establecimiento establecimiento) {
		GrupoDeDistribucion grupoEncontrado = new GrupoDeDistribucion();

		for (GrupoDeDistribucion grupo : this.grupos) {
			if (this.perteneceAlGrupo(establecimiento, grupo)) {
				grupoEncontrado = grupo;
				break;
			}
		}

		return grupoEncontrado;
	}
}
