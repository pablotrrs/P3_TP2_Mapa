package tests;

import static org.junit.Assert.assertEquals;
import java.util.ArrayList;
import org.junit.Test;
import org.openstreetmap.gui.jmapviewer.Coordinate;
import org.openstreetmap.gui.jmapviewer.MapPolygonImpl;
import grafo.Grafo;
import tp.Negocio;
import tp.Tupla;

public class NegocioTest {

	// generarGrafo(), contectarAGM() y conectarCompleto()

	@Test
	public void testConectarAGM() {
		ArrayList<Tupla<String, Coordinate>> localidades = crearLista();

		Grafo grafo = new Grafo(localidades.size());
		grafo.agregarArista(0, 1, 0);
		grafo.agregarArista(0, 2, 0);

		ArrayList<MapPolygonImpl> esperado = crearEsperado();
		ArrayList<MapPolygonImpl> obtenido = Negocio.conectarPoligonoAGM(localidades, grafo);

		assertEquals(true, comparar(esperado, obtenido));
	}

	@Test
	public void testConectarCompleto() {
		ArrayList<Tupla<String, Coordinate>> localidades = crearLista();

		MapPolygonImpl esperado = poligonoEsperado();
		MapPolygonImpl obtenido = Negocio.conectarCompleto(localidades);

		Assert.poligonosIguales(obtenido, esperado);
	}

	@Test
	public void testGenerarAGM() {
		ArrayList<Tupla<String, Coordinate>> localidades = crearLista();
		
		ArrayList<MapPolygonImpl> esperado = crearEsperado2();
		ArrayList<MapPolygonImpl> obtenido = Negocio.generarAGM(localidades);
		
		assertEquals(true, comparar(esperado, obtenido));
	}

	private static boolean comparar(ArrayList<MapPolygonImpl> esperado, ArrayList<MapPolygonImpl> obtenido) {
		boolean ret = true;
		for (int i = 0; i < esperado.size(); i++) {
			Assert.poligonosIguales(obtenido.get(i), esperado.get(i));
		}
		return ret;
	}

	private MapPolygonImpl poligonoEsperado() {
		ArrayList<Coordinate> coordenadas = new ArrayList<Coordinate>();
		coordenadas.add(new Coordinate(-34.6131516, -58.3772316));
		coordenadas.add(new Coordinate(-32.8908386, -68.8271713));
		coordenadas.add(new Coordinate(-24.1945705, -65.2971191));

		coordenadas.add(new Coordinate(-32.8908386, -68.8271713));
		coordenadas.add(new Coordinate(-34.6131516, -58.3772316));
		coordenadas.add(new Coordinate(-24.1945705, -65.2971191));

		coordenadas.add(new Coordinate(-24.1945705, -65.2971191));
		coordenadas.add(new Coordinate(-34.6131516, -58.3772316));
		coordenadas.add(new Coordinate(-32.8908386, -68.8271713));

		return new MapPolygonImpl(coordenadas);
	}

	private ArrayList<MapPolygonImpl> crearEsperado() {
		ArrayList<MapPolygonImpl> esperado = new ArrayList<MapPolygonImpl>();

		ArrayList<Coordinate> conectados1 = new ArrayList<Coordinate>();
		conectados1.add(new Coordinate(-34.6131516, -58.3772316));
		conectados1.add(new Coordinate(-32.8908386, -68.8271713));
		conectados1.add(new Coordinate(-32.8908386, -68.8271713));

		ArrayList<Coordinate> conectados2 = new ArrayList<Coordinate>();
		conectados2.add(new Coordinate(-34.6131516, -58.3772316));
		conectados2.add(new Coordinate(-24.1945705, -65.2971191));
		conectados2.add(new Coordinate(-24.1945705, -65.2971191));

		esperado.add(new MapPolygonImpl(conectados1));
		esperado.add(new MapPolygonImpl(conectados2));
		return esperado;
	}
	
	private ArrayList<MapPolygonImpl> crearEsperado2() {
		ArrayList<MapPolygonImpl> esperado = new ArrayList<MapPolygonImpl>();

		ArrayList<Coordinate> conectados1 = new ArrayList<Coordinate>();
		conectados1.add(new Coordinate(-34.6131516, -58.3772316));
		conectados1.add(new Coordinate(-32.8908386, -68.8271713));
		conectados1.add(new Coordinate(-32.8908386, -68.8271713));

		ArrayList<Coordinate> conectados2 = new ArrayList<Coordinate>();
		conectados2.add(new Coordinate(-32.8908386, -68.8271713));
		conectados2.add(new Coordinate(-24.1945705, -65.2971191));
		conectados2.add(new Coordinate(-24.1945705, -65.2971191));

		esperado.add(new MapPolygonImpl(conectados1));
		esperado.add(new MapPolygonImpl(conectados2));
		return esperado;
	}

	private ArrayList<Tupla<String, Coordinate>> crearLista() {
		ArrayList<Tupla<String, Coordinate>> lista = new ArrayList<Tupla<String, Coordinate>>();
		lista.add(new Tupla<String, Coordinate>("buenos aires", new Coordinate(-34.6131516, -58.3772316)));
		lista.add(new Tupla<String, Coordinate>("mendoza", new Coordinate(-32.8908386, -68.8271713)));
		lista.add(new Tupla<String, Coordinate>("jujuy", new Coordinate(-24.1945705, -65.2971191)));
		return lista;
	}
}
