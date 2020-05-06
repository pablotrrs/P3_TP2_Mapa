package tp;

import java.util.ArrayList;

import org.openstreetmap.gui.jmapviewer.Coordinate;

public class Tupla<T1, T2> {

	private T1 primerElemento;
	private T2 segundoElemento;

	public Tupla(T1 primerElemento, T2 segundoElemento) {
		this.primerElemento = primerElemento;
		this.segundoElemento = segundoElemento;
	}

	public T1 getPrimerElemento() {
		return primerElemento;
	}

	public T2 getSegundoElemento() {
		return segundoElemento;
	}
	
	// Indica si el ArrayList contiene la Tupla pasada como parametro
	public static boolean contiene(ArrayList<Tupla<String, Coordinate>> puntosLocalidades,
			Tupla<String, Coordinate> punto) {
		boolean ret = false;
		for (int i = 0; i < puntosLocalidades.size(); i++) {
			ret |= puntosLocalidades.get(i).equals(punto);
		}
		return ret;
	}

	@Override
	public boolean equals(Object objeto) {
		if (this == objeto)
			return true;

		if (objeto == null || objeto.getClass() != this.getClass())
			return false;

		Tupla<?, ?> tupla = (Tupla<?, ?>) objeto;
		return tupla.getPrimerElemento().equals(this.getPrimerElemento())
				&& tupla.getSegundoElemento().equals(this.getSegundoElemento());
	}

	public String toString() {
		return "Tupla: {" + this.getPrimerElemento() + ", " + this.getSegundoElemento() + "}";
	}
}
