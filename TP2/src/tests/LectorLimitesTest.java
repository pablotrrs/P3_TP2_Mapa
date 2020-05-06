package tests;

import static org.junit.Assert.*;
import java.io.IOException;
import java.util.TreeMap;
import org.junit.Test;
import org.openstreetmap.gui.jmapviewer.MapPolygonImpl;
import tp.LectorLimites;

public class LectorLimitesTest {

	@Test
	public void testCantidadClaves() throws IOException {
		TreeMap<String, MapPolygonImpl> limitesProvinciales = LectorLimites.limitesProvinciales();
		int cantidadProvincias = limitesProvinciales.size();

		assertEquals(23, cantidadProvincias);
	}

	@Test
	public void testCantidadValores() throws IOException {
		TreeMap<String, MapPolygonImpl> limitesProvinciales = LectorLimites.limitesProvinciales();
		int cantidadLimites = limitesProvinciales.values().size();

		assertEquals(23, cantidadLimites);
	}

	@Test
	public void testExisteClave() throws IOException {
		TreeMap<String, MapPolygonImpl> limitesProvinciales = LectorLimites.limitesProvinciales();

		assertTrue(limitesProvinciales.containsKey("buenos aires"));
	}

	@Test
	public void tesNotExisteClave() throws IOException {
		TreeMap<String, MapPolygonImpl> limitesProvinciales = LectorLimites.limitesProvinciales();

		assertFalse(limitesProvinciales.containsKey("california"));
	}

}
