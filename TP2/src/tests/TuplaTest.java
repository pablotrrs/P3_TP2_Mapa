package tests;

import static org.junit.Assert.*;
import java.util.ArrayList;
import org.junit.Test;
import org.openstreetmap.gui.jmapviewer.Coordinate;
import tp.Tupla;

public class TuplaTest {

	@SuppressWarnings("null")
	@Test(expected = NullPointerException.class)
	public void testNulls() {
		Tupla<String, Coordinate> coordenada1 = null;
		Tupla<String, Coordinate> coordenada2 = null;
		coordenada1.equals(coordenada2);
	}

	@SuppressWarnings("null")
	@Test(expected = NullPointerException.class)
	public void testACompararNull() {
		Tupla<String, Coordinate> coordenada1 = null;
		Tupla<String, Coordinate> coordenada2 = new Tupla<String, Coordinate>("Buenos Aires",
				new Coordinate(-34.6131516, -58.3772316));
		coordenada1.equals(coordenada2);
	}

	@Test
	public void testACompararNoNull() {
		Tupla<String, Coordinate> coordenada1 = new Tupla<String, Coordinate>("Buenos Aires",
				new Coordinate(-34.6131516, -58.3772316));
		Tupla<String, Coordinate> coordenada2 = null;
		assertFalse(coordenada1.equals(coordenada2));
	}

	@Test
	public void testIguales() {
		Tupla<String, Coordinate> coordenada1 = new Tupla<String, Coordinate>("Buenos Aires",
				new Coordinate(-34.6131516, -58.3772316));
		Tupla<String, Coordinate> coordenada2 = new Tupla<String, Coordinate>("Buenos Aires",
				new Coordinate(-34.6131516, -58.3772316));

		assertTrue(coordenada1.equals(coordenada2));
	}

	@Test
	public void testDiferentes() {
		Tupla<String, Coordinate> coordenada1 = new Tupla<String, Coordinate>("Buenos Aires",
				new Coordinate(-34.6131516, -58.3772316));
		Tupla<String, Coordinate> coordenada2 = new Tupla<String, Coordinate>("Rosario",
				new Coordinate(-32.9468193, -60.6393204));

		assertFalse(coordenada1.equals(coordenada2));
	}

	@Test
	public void testComparoConSi() {
		Tupla<String, Coordinate> coordenada1 = new Tupla<String, Coordinate>("Buenos Aires",
				new Coordinate(-34.6131516, -58.3772316));

		assertTrue(coordenada1.equals(coordenada1));
	}

	@Test
	public void testClasesDistintas() {
		Tupla<String, Coordinate> coordenada = new Tupla<String, Coordinate>("Buenos Aires",
				new Coordinate(-34.6131516, -58.3772316));
		Tupla<String, Integer> persona = new Tupla<String, Integer>("Juan Carlos", 18);

		assertFalse(coordenada.equals(persona));
	}
	
	@Test
	public void testContienePuntoPrimeraPosicion() {
		ArrayList<Tupla<String, Coordinate>> lista = crearLista();
		Tupla<String, Coordinate> punto = new Tupla<String, Coordinate>("buenos aires",
				new Coordinate(-34.6131516, -58.3772316));

		assertTrue(Tupla.contiene(lista, punto));
	}

	@Test
	public void testContienePuntoUltimaPosicion() {
		ArrayList<Tupla<String, Coordinate>> lista = crearLista2();
		Tupla<String, Coordinate> punto = new Tupla<String, Coordinate>("buenos aires",
				new Coordinate(-34.6131516, -58.3772316));

		assertTrue(Tupla.contiene(lista, punto));
	}

	@Test
	public void testContienePuntoNormal() {
		ArrayList<Tupla<String, Coordinate>> lista = crearLista3();
		Tupla<String, Coordinate> punto = new Tupla<String, Coordinate>("buenos aires",
				new Coordinate(-34.6131516, -58.3772316));

		assertTrue(Tupla.contiene(lista, punto));
	}
	
	private ArrayList<Tupla<String, Coordinate>> crearLista() {
		ArrayList<Tupla<String, Coordinate>> lista = new ArrayList<Tupla<String, Coordinate>>();
		lista.add(new Tupla<String, Coordinate>("buenos aires", new Coordinate(-34.6131516, -58.3772316)));
		lista.add(new Tupla<String, Coordinate>("mendoza", new Coordinate(-32.8908386, -68.8271713)));
		lista.add(new Tupla<String, Coordinate>("jujuy", new Coordinate(-24.1945705, -65.2971191)));
		return lista;
	}

	private ArrayList<Tupla<String, Coordinate>> crearLista2() {
		ArrayList<Tupla<String, Coordinate>> lista = new ArrayList<Tupla<String, Coordinate>>();
		lista.add(new Tupla<String, Coordinate>("mendoza", new Coordinate(-32.8908386, -68.8271713)));
		lista.add(new Tupla<String, Coordinate>("jujuy", new Coordinate(-24.1945705, -65.2971191)));
		lista.add(new Tupla<String, Coordinate>("buenos aires", new Coordinate(-34.6131516, -58.3772316)));
		return lista;
	}

	private ArrayList<Tupla<String, Coordinate>> crearLista3() {
		ArrayList<Tupla<String, Coordinate>> lista = new ArrayList<Tupla<String, Coordinate>>();
		lista.add(new Tupla<String, Coordinate>("mendoza", new Coordinate(-32.8908386, -68.8271713)));
		lista.add(new Tupla<String, Coordinate>("buenos aires", new Coordinate(-34.6131516, -58.3772316)));
		lista.add(new Tupla<String, Coordinate>("jujuy", new Coordinate(-24.1945705, -65.2971191)));
		return lista;
	}
}
