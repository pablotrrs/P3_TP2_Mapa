package tp;

import org.openstreetmap.gui.jmapviewer.Coordinate;

public class Haversine {

	// Usamos la formula Haversine para calcular la distancia entre dos coordenadas
	public static double distancia(Coordinate coordenada1, Coordinate coordenada2) {
		if (coordenada1 == null || coordenada2 == null)
			throw new NullPointerException("La coordenada pasada no puede ser null!");

		int R = 6371; // Radio del planeta tierra
		double latDistancia = aRadianes(coordenada2.getLat() - coordenada1.getLat());
		double lonDistancia = aRadianes(coordenada2.getLon() - coordenada1.getLon());
		double a = Math.sin(latDistancia / 2) * Math.sin(latDistancia / 2) + Math.cos(aRadianes(coordenada1.getLat()))
				* Math.cos(aRadianes(coordenada2.getLat())) * Math.sin(lonDistancia / 2) * Math.sin(lonDistancia / 2);
		double c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1 - a));
		double distancia = R * c;

		return distancia;
	}

	private static double aRadianes(double valor) {
		return valor * Math.PI / 180;
	}
}
