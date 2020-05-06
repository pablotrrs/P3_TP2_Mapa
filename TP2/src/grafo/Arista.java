package grafo;

import java.util.Comparator;

public class Arista implements Comparator<Arista> {
	private int origen; // Vertice origen
	private int destino; // Vertice destino
	private double peso; // Peso entre el vertice origen y destino

	public Arista() {

	}

	public Arista(int origen, int destino, double peso2) {
		this.origen = origen;
		this.destino = destino;
		this.peso = peso2;
	}

	// Comparador por peso para ordenar en orden ascendente y obtener el AGM
	@Override
	public int compare(Arista e1, Arista e2) {
		// return e2.peso - e1.peso; //Arbol de expansion maxima
		return (int)(e1.peso - e2.peso); // Arbol de expansion minima
	}

	public int getOrigen() {
		return origen;
	}

	public void setOrigen(int origen) {
		this.origen = origen;
	}

	public int getDestino() {
		return destino;
	}

	public void setDestino(int destino) {
		this.destino = destino;
	}

	public double getPeso() {
		return peso;
	}

	public void setPeso(int peso) {
		this.peso = peso;
	}
};
