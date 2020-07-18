package ar.edu.unlam.tallerweb1.comun.modelos.negocio;

public class GrupoDeDistribucion {
	private Integer puntajeMinimo;
	private Integer puntajeMaximo;
	private Float porcentajeADistribuir;
	private Integer cantidad;

	public GrupoDeDistribucion(Integer puntajeMinimo, Integer puntajeMaximo, Float porcentajeADistribuir) {
		this.puntajeMinimo = puntajeMinimo;
		this.puntajeMaximo = puntajeMaximo;
		this.porcentajeADistribuir = porcentajeADistribuir;
		this.cantidad = 0;
	}

	public GrupoDeDistribucion() {
		this.cantidad = 0;
	}

	public Integer getPuntajeMinimo() {
		return puntajeMinimo;
	}

	public void setPuntajeMinimo(Integer puntajeMinimo) {
		this.puntajeMinimo = puntajeMinimo;	
	}

	public Integer getPuntajeMaximo() {
		return puntajeMaximo;
	}

	public void setPuntajeMaximo(Integer puntajeMaximo) {
		this.puntajeMaximo = puntajeMaximo;
	}

	public Float getPorcentajeADistribuir() {
		return porcentajeADistribuir;
	}

	public void setPorcentajeADistribuir(Float porcentajeADistribuir) {
		this.porcentajeADistribuir = porcentajeADistribuir;
	}

	public Integer getCantidad() {
		return cantidad;
	}

	public void setCantidad(Integer cantidad) {
		this.cantidad = cantidad;
	}
}
