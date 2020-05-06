package grafo;

import java.util.Arrays;

public class Prim {

	// Ejecuta el algoritmo de Prim sobre el grafo pasado como parametro
	public static Grafo calcular(Grafo grafo) {
		int n = grafo.tamano(), cantAristas = 0, raiz = 0, actual = 0;
		Grafo AGM = new Grafo(n);
		boolean[] marcados = new boolean[n];
		double[] pesos = new double[n];
		int[] predecesores = new int[n];

		Arrays.fill(pesos, Integer.MAX_VALUE);
		Arrays.fill(predecesores, -1);

		pesos[raiz] = 0;

		while (true) {
			actual = (int) buscarVerticeConMinimaLongitud(marcados, pesos, n);

			// si recorrio todos los vertices posibles
			if (actual == -1) {
				// si ya formo el AGM
				if (cantAristas == n - 1) {
					return AGM;
				} else {
					throw new IllegalArgumentException("El grafo no es conexo.");
				}
			}

			// marco al actual (lo pongo en "amarillo")
			marcados[actual] = true;

			// Agrego arista en el AGM
			if (actual != raiz) {
				cantAristas++; // cuento la arista
				AGM.agregarArista(predecesores[actual], actual, grafo.pesoArista(predecesores[actual], actual));
			}

			for (int vertice = 0; vertice < n; vertice++) {
				if (vertice != actual && grafo.existeArista(actual, vertice) && !marcados[vertice]
						&& pesos[vertice] > grafo.pesoArista(actual, vertice)) {
					pesos[vertice] = grafo.pesoArista(actual, vertice);
					predecesores[vertice] = actual;

				}
			}
		}
	}

	private static double buscarVerticeConMinimaLongitud(boolean[] marcados, double[] pesos, int cantVertices) {
		double verticeMinimo = -1, pesoMinimo = Integer.MAX_VALUE;
		for (int vertice = 0; vertice < cantVertices; vertice++) {
			if (!marcados[vertice] && pesoMinimo > pesos[vertice]) {
				pesoMinimo = pesos[vertice];
				verticeMinimo = vertice;
			}
		}
		return verticeMinimo;
	}

}
