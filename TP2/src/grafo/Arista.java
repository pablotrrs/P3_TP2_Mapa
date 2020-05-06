package grafo;

import java.util.Comparator;

public class Arista implements Comparator<Arista> {
	private int extremo1; // Vertice origen
	private int extremo2; // Vertice destino
	private double peso; // Peso entre el vertice origen y destino

	public Arista() {

	}

	public Arista(int origen, int destino, double peso2) {
		this.extremo1 = origen;
		this.extremo2 = destino;
		this.peso = peso2;
	}

	// Comparador por peso para ordenar en orden ascendente y obtener el AGM
	@Override
	public int compare(Arista e1, Arista e2) {
		// return e2.peso - e1.peso; //Arbol de expansion maxima
		return (int)(e1.peso - e2.peso); // Arbol de expansion minima
	}

	public int getOrigen() {
		return extremo1;
	}

	public void setOrigen(int origen) {
		this.extremo1 = origen;
	}

	public int getDestino() {
		return extremo2;
	}

	public void setDestino(int destino) {
		this.extremo2 = destino;
	}

	public double getPeso() {
		return peso;
	}

	public void setPeso(int peso) {
		this.peso = peso;
	}
};
