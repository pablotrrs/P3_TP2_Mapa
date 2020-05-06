package tests;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import java.util.Set;

import org.openstreetmap.gui.jmapviewer.MapPolygonImpl;

public class Assert {
	// Verifica que sean iguales como conjuntos
	public static void iguales(int[] esperado, Set<Integer> obtenido) {
		assertEquals(esperado.length, obtenido.size());

		for (int i = 0; i < esperado.length; ++i)
			assertTrue(obtenido.contains(esperado[i]));
	}

	// Verifica que los poligonos sean iguales comparando a los puntos que los conforman a estos 
	public static void poligonosIguales(MapPolygonImpl obtenido, MapPolygonImpl esperado) {
		assertEquals(esperado.getPoints().size(), obtenido.getPoints().size());
		
		for (int i = 0; i < esperado.getPoints().size(); i++) {
			assertEquals(esperado.getPoints().get(i), obtenido.getPoints().get(i));
		}
	}
}
