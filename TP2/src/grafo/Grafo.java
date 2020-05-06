package grafo;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Set;

public class Grafo {
	protected Arista[] grafo;
	protected int cantidadVertices;
	protected int cantidadAristas;
	protected int pos;
	protected double peso;

	public Grafo(int v) {
		pos = 0;
		cantidadAristas = 0;
		cantidadVertices = v;
		grafo = new Arista[1000];
	}

	public void agregarArista(int origen, int destino, double peso) {
		verificarVertice(origen, destino);
		verificarLoop(origen, destino);

		if (!existeArista(origen, destino)) {
			grafo[pos] = new Arista(origen, destino, peso);
			pos++;
			cantidadAristas++;
			this.peso += peso;
		}
	}

	public void eliminarArista(int origen, int destino) {
		verificarVertice(origen, destino);
		verificarLoop(origen, destino);

		for (int i = 0; i < pos; i++) {
			if (grafo[i] != null) {
				if ((grafo[i].getExtremo1() == origen && grafo[i].getExtremo2() == destino)) {
					this.peso -= grafo[i].getPeso();
					grafo[i] = null;
					cantidadAristas--;
				}
			}
		}
	}

	public boolean existeArista(int origen, int destino) {
		verificarVertice(origen, destino);
		verificarLoop(origen, destino);
		boolean bool = false;
		for (int i = 0; i < pos; i++) {
			if (grafo[i] != null) {
				if ((grafo[i].getExtremo1() == origen && grafo[i].getExtremo2() == destino)
						|| ((grafo[i].getExtremo1() == destino && grafo[i].getExtremo2() == origen))) {
					bool = true;
				}
			}
		}
		return bool;
	}

	// Retorna un conjunto con los vecinos del vertice pasado como parametro
	public Set<Integer> vecinos(int origen) {
		verificarVertice(origen, origen);
		Set<Integer> ret = new HashSet<Integer>();
		for (int i = 0; i < pos; i++) {
			if (grafo[i] != null && grafo[i].getExtremo1() == origen) {
				ret.add(grafo[i].getExtremo2());
			}
			if (grafo[i] != null && grafo[i].getExtremo2() == origen) {
				ret.add(grafo[i].getExtremo1());
			}
		}
		return ret;
	}

	// Retorna el peso de la arista pasada como parametro
	public double pesoArista(int origen, int destino) {
		double peso = 0;

		for (int i = 0; i < pos; i++) {
			if (grafo[i] != null && (grafo[i].getExtremo1() == origen && grafo[i].getExtremo2() == destino)
					|| (grafo[i].getExtremo1() == destino && grafo[i].getExtremo2() == origen)) {
				peso = grafo[i].getPeso();
			}
		}
		return peso;
	}

	public int tamano() {
		return cantidadVertices;
	}

	public int cantidadAristas() {
		return cantidadAristas;
	}

	public String toString() {
		String s = "";
		for (int i = 0; i < pos; i++) {
			if (grafo[i] != null)
				s += grafo[i].getExtremo1() + " = {" + grafo[i].getExtremo2() + "(" + grafo[i].getPeso() + ")} ";
		}
		s += ".";
		return s;
	}

	public boolean esConexo(Integer s) {
		Set<Integer> marcados = new HashSet<Integer>();
		Queue<Integer> L = new LinkedList<Integer>();

		if (tamano() == 0)
			return true;

		L.add(s);

		while (!L.isEmpty()) {

			s = L.remove();
			marcados.add(s);
			agregarVecinosPendientes(this, s, L, marcados);
		}
		return tamano() == marcados.size();

	}

	private void agregarVecinosPendientes(Grafo g, Integer s, Queue<Integer> L, Set<Integer> marcados) {
		for (Integer vertice : g.vecinos(s)) {
			if (!L.contains(vertice) && !marcados.contains(vertice)) {
				L.add(vertice);
			}
		}
	}

	// Indica si dos vertices se encuentran a distancia dos en un grafo
	public boolean distanciaDos(Integer v1, Integer v2) {
		if (tamano() == 0)
			return true;

		boolean ret = false;
		for (Integer vertice : vecinos(v1)) {
			for (Integer vecinosV2 : vecinos(vertice)) {
				if (vecinosV2 == v2 && !existeArista(v1, vecinosV2)) {
					ret |= true;
					break;
				}
			}
		}
		return ret;
	}

	// Indica la cantidad de triangulos que hay en un grafo
	public Integer cantidadTriangulos(Integer s) {
		Set<Integer> marcados = new HashSet<Integer>();
		Queue<Integer> L = new LinkedList<Integer>();
		Integer cantidad = 0;

		L.add(s);

		while (!L.isEmpty()) {
			ArrayList<Integer> vecinos = new ArrayList<Integer>();
			s = L.remove();
			for (Integer vertice : vecinos(s)) {
				if (!L.contains(vertice) && !marcados.contains(vertice)) {
					L.add(vertice);
				}
				vecinos.add(vertice);
			}
			for (int i = 0; i < vecinos.size(); i++) {
				for (int j = i + 1; j < vecinos.size(); j++) {
					if (existeArista(vecinos.get(i), vecinos.get(j)) && !marcados.contains(vecinos.get(i)))
						cantidad++;
				}
			}
			marcados.add(s);
		}
		return cantidad;
	}

	public boolean esUniversal(Integer s) {
		Set<Integer> marcados = new HashSet<Integer>();
		Queue<Integer> L = new LinkedList<Integer>();
		boolean ret = true;
		Integer aux = s;
		L.add(s);

		while (!L.isEmpty()) {
			s = L.remove();
			if (s != aux)
				ret &= vecinos(s).contains(aux);
			marcados.add(s);
		}

		return ret;
	}

	public int distancia(int vertice1, int vertice2) {
		Queue<Integer> L = new LinkedList<Integer>();
		int[] distancias = new int[tamano()];
		Arrays.fill(distancias, -1);

		if (tamano() == 0)
			return -1;

		L.add(vertice1);
		distancias[vertice1] = 0;

		while (!L.isEmpty()) {

			vertice1 = L.remove();
			for (Integer vertice : vecinos(vertice1)) {
				if (distancias[vertice] == -1) {
					distancias[vertice] = distancias[vertice1] + 1;
					L.add(vertice);
				}
			}

		}
		return distancias[vertice2];
	}

	protected void verificarLoop(int origen, int destino) {
		if (origen == destino) {
			throw new IllegalArgumentException("No se permiten loops: (" + origen + ", " + destino + ")");
		}
	}

	protected void verificarVertice(int origen, int destino) {
		if (origen >= cantidadVertices || destino >= cantidadVertices || origen < 0 || destino < 0) {
			throw new IllegalArgumentException("Los vertices deben estar dentro de 0 y |V|-1.");
		}
	}

	public double getPeso() {
		return peso;
	}
}
