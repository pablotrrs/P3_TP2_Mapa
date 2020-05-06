package tests;

import static org.junit.Assert.*;
import org.junit.Test;
import org.openstreetmap.gui.jmapviewer.Coordinate;
import tp.Haversine;

public class HaversineTest {

	@Test(expected = NullPointerException.class)
	public void testPuntoNull() {
		Coordinate coordenada1 = null;
		Coordinate coordenada2 = new Coordinate(-26.8241405, -65.2226028);// San Miguel de Tucuman

		Haversine.distancia(coordenada1, coordenada2);
	}

	@Test
	public void testPuntosDistintos() {
		Coordinate coordenada1 = new Coordinate(-34.6131516, -58.3772316); // Buenos Aires
		Coordinate coordenada2 = new Coordinate(-26.8241405, -65.2226028);// San Miguel de Tucuman

		double distancia = Haversine.distancia(coordenada1, coordenada2);

		assertEquals(1085, Math.round(distancia));
	}

	@Test
	public void testPuntosIguales() {
		Coordinate coordenada1 = new Coordinate(-34.6131516, -58.3772316); // Buenos Aires

		double distancia = Haversine.distancia(coordenada1, coordenada1);

		assertEquals(0, Math.round(distancia));
	}

}
