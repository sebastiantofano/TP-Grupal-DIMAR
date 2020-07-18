package ar.edu.unlam.tallerweb1.configuracion;

import java.beans.PropertyEditorSupport;

import ar.edu.unlam.tallerweb1.comun.enums.TipoDeStrategy;

public class StringToTipoDeStrategy extends PropertyEditorSupport {

	@Override
	public void setAsText(String text) throws IllegalArgumentException {
		String capitalized = text.toUpperCase();

		try {
			TipoDeStrategy prioridad = TipoDeStrategy.valueOf(capitalized);
			this.setValue(prioridad);
		} catch (Exception ex) {
			this.setValue(TipoDeStrategy.COMBINADO);
		}
	}
}