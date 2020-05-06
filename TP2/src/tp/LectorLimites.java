package tp;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.TreeMap;
import org.openstreetmap.gui.jmapviewer.Coordinate;
import org.openstreetmap.gui.jmapviewer.MapPolygonImpl;

public class LectorLimites {
	private static int puntero;
	private static ArrayList<String> provincias;
	private static ArrayList<Coordinate> limitesProvincias;
	private static TreeMap<String, MapPolygonImpl> poligonosProvincias;

	@SuppressWarnings("resource")
	public static TreeMap<String, MapPolygonImpl> limitesProvinciales() throws IOException {
		try {
			java.io.FileReader archivo = new java.io.FileReader("limites.txt");
			puntero = 0;
			int valorCaracter;
			String nombreProvincia = "", puntos = "";
			provincias = new ArrayList<String>();
			poligonosProvincias = new TreeMap<String, MapPolygonImpl>();

			while ((valorCaracter = archivo.read()) != -1) {
				char caracter = (char) valorCaracter;
				if (esLetra(caracter)) {
					nombreProvincia += caracter;
				} else if (esNumero(caracter)) {
					puntos += caracter;
				}

				if (caracter == ':') {
					provincias.add(nombreProvincia.toLowerCase());
					nombreProvincia = "";
				}

				if (caracter == '*') {
					almacenarEnArrayList(puntos);
					puntos = "";
					poligonosProvincias.put(provincias.get(puntero++), new MapPolygonImpl(limitesProvincias));
				}
			}
		} catch (FileNotFoundException e1) {
			System.out.println(e1 + ".");
		}
		return poligonosProvincias;
	}

	private static boolean esNumero(char caracter) {
		return caracter != '{' && caracter != '}' && caracter != '*' && caracter != ':';
	}

	private static boolean esLetra(char caracter) {
		return caracter != '-' && caracter != '.' && caracter != ',' && caracter != '[' && caracter != ']'
				&& caracter != '{' && caracter != '}' && caracter != '0' && caracter != '1' && caracter != '2'
				&& caracter != '3' && caracter != '4' && caracter != '5' && caracter != '6' && caracter != '7'
				&& caracter != '8' && caracter != '9' && caracter != '*' && caracter != ':';
	}

	private static void almacenarEnArrayList(String puntos) {
		String punto = "";
		Coordinate coordenada = new Coordinate(0, 0);
		limitesProvincias = new ArrayList<Coordinate>();
		
		for (int i = 0; i < puntos.length(); i++) {
			if (puntos.charAt(i) == '[') {
				punto = "";
			}
			if (puntos.charAt(i) != ',' && puntos.charAt(i) != '[' && puntos.charAt(i) != ']') {
				punto += puntos.charAt(i);
			}

			if (puntos.charAt(i) == ',') {
				coordenada.setLat(Double.valueOf(punto));
				punto = "";
			}

			if (puntos.charAt(i) == ']') {
				coordenada.setLon(Double.valueOf(punto));
				limitesProvincias.add(coordenada);
				coordenada = new Coordinate(0, 0);
			}
		}
	}
}
