package ar.edu.unlam.tallerweb1.repositorios;

import ar.edu.unlam.tallerweb1.modelo.Insumo;

public interface RepositorioInsumo extends RepositorioBase<Insumo, Long> {

//  Retorna la cantidad de insumos disponibles, realiza un SUM del campo 'cantidad'
	Long cantTotalInsumos();
}
