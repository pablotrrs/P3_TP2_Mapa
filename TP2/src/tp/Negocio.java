package tp;

import java.util.TreeMap;
import java.io.IOException;
import java.util.ArrayList;
import org.openstreetmap.gui.jmapviewer.Coordinate;
import org.openstreetmap.gui.jmapviewer.MapPolygonImpl;
import grafo.Grafo;
import grafo.Prim;

public class Negocio {

	private static final double COSTOKM = 1600, PORCENTAJEAUMENTO = 0.33, CONEXIONACIONAL = 3600;
	protected static double costoAGM = 0;

	// Guardamos los puntos de un poligono tomado de una lista en un arreglo para
	// poder usar la clase SolverPoligono (para poder determinar si un punto se
	// encuentra dentro de un poligono)
	@SuppressWarnings("unchecked")
	public static Point[] toArray(String clave) throws IOException {
		TreeMap<String, MapPolygonImpl> limitesProvinciales = LectorLimites.limitesProvinciales();

		if (!limitesProvinciales.containsKey(clave.toLowerCase()))
			return null;

		ArrayList<Coordinate> coordenadas = (ArrayList<Coordinate>) limitesProvinciales.get(clave.toLowerCase())
				.getPoints();
		Point poligonoArray[] = new Point[coordenadas.size()];

		for (int i = 0; i < coordenadas.size(); i++) {
			poligonoArray[i] = new Point(coordenadas.get(i).getLat(), coordenadas.get(i).getLon());
		}

		return poligonoArray;
	}

	// Generamos el AGM con los puntos pasados y luego devolvemos un ArrayList de
	// poligonos para dibujar la soulucion en el mapa
	public static ArrayList<MapPolygonImpl> generarAGM(ArrayList<Tupla<String, Coordinate>> localidades) {
		int vertices = localidades.size();
		Grafo grafo = new Grafo(vertices);

		for (int i = 0; i < localidades.size() - 1; i++) {
			Coordinate coordenada1 = localidades.get(i).getSegundoElemento();
			for (int j = i + 1; j < localidades.size(); j++) {
				Coordinate coordenada2 = localidades.get(j).getSegundoElemento();
				double distancia = Haversine.distancia(coordenada1, coordenada2);

				double costo = distancia * COSTOKM;
				if (distancia > 300) {
					costo += costo * PORCENTAJEAUMENTO;
				}

				if (puntosEnProvinciasDiferentes(localidades, i, j)) {
					costo += CONEXIONACIONAL;
				}

				grafo.agregarArista(i, j, costo);
			}
		}
		grafo = Prim.calcular(grafo);
		costoAGM = grafo.getPeso();

		return conectarPoligonoAGM(localidades, grafo);
	}

	private static boolean puntosEnProvinciasDiferentes(ArrayList<Tupla<String, Coordinate>> localidades, int i,
			int j) {
		return !localidades.get(i).getPrimerElemento().equals(localidades.get(j).getPrimerElemento());
	}

	// Conecta los puntos de a 2 con una linea y forma un poligono para cada uno
	public static ArrayList<MapPolygonImpl> conectarPoligonoAGM(ArrayList<Tupla<String, Coordinate>> localidades,
			Grafo grafo) {
		ArrayList<MapPolygonImpl> poligonos = new ArrayList<MapPolygonImpl>();

		for (int i = 0; i < localidades.size() - 1; i++) {
			for (int j = i + 1; j < localidades.size(); j++) {
				ArrayList<Coordinate> conectarPuntos = new ArrayList<Coordinate>();

				if (grafo.existeArista(i, j)) {
					conectarPuntos.add(localidades.get(i).getSegundoElemento());
					conectarPuntos.add(localidades.get(j).getSegundoElemento());
					conectarPuntos.add(localidades.get(j).getSegundoElemento());

					poligonos.add(new MapPolygonImpl(conectarPuntos));
				}
			}
		}
		return poligonos;
	}
}
