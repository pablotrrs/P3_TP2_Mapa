package tests;

import static org.junit.Assert.*;
import org.junit.Test;
import grafo.Grafo;

public class BFSTest {

	@Test(expected = IllegalArgumentException.class)
	public void grafoExcepcionTest() {
		Grafo grafo = new Grafo(4);
		assertTrue(grafo.esConexo(8));
	}

	@Test
	public void grafoVacioTest() {
		Grafo grafo = new Grafo(0);
		assertTrue(grafo.esConexo(0));
	}

	@Test
	public void esConexoTest() {
		Grafo grafo = inicializarGrafo();
		assertTrue(grafo.esConexo(0));
	}

	@Test
	public void noEsConexoTest() {
		Grafo grafo = new Grafo(6);
		grafoNoConexo(grafo);
		assertFalse(grafo.esConexo(0));
	}

	@Test
	public void distanciaDosTest() {
		Grafo grafo = inicializarGrafo();
		assertTrue(grafo.distanciaDos(0, 3));
	}

	@Test
	public void noEsDistanciaDosTest() {
		Grafo grafo = inicializarGrafo();
		assertFalse(grafo.distanciaDos(0, 2));
	}

	@Test
	public void primerCantidadTriangulosTest() {
		Grafo grafo = inicializarGrafo();
		grafo.agregarArista(1, 2, 1);
		assertEquals(1, (int) grafo.cantidadTriangulos(0));
	}

	@Test
	public void segundoCantidadTriangulosTest() {
		Grafo grafo = new Grafo(5);
		inicializarGrafo2(grafo);

		assertEquals(3, (int) grafo.cantidadTriangulos(0));
	}

	@Test
	public void tercerCantidadTriangulosTest() {
		Grafo grafo = new Grafo(6);
		inicializarGrafo3(grafo);
		assertEquals(4, (int) grafo.cantidadTriangulos(0));
	}

	@Test
	public void cuartaCantidadTriangulosTest() {
		Grafo grafo = grafoPizza();
		assertEquals(8, (int) grafo.cantidadTriangulos(0));
	}

	@Test
	public void noTriangulosTest() {
		Grafo grafo = inicializarGrafo();
		assertEquals(0, (int) grafo.cantidadTriangulos(0));
	}

	@Test
	public void verticeUniversalTest() {
		Grafo grafo = grafoPizza();
		assertTrue(grafo.esUniversal(0));
	}

	@Test
	public void distancias0Test() {
		Grafo grafo = new Grafo(2);
		grafo.agregarArista(0, 1, 1);
		assertEquals(0, (int) grafo.distancia(0, 0));
	}

	@Test
	public void distancias1Test() {
		Grafo grafo = new Grafo(5);
		grafoDistancias(grafo);
		assertEquals(1, (int) grafo.distancia(0, 1));
		assertEquals(1, (int) grafo.distancia(0, 2));
	}

	@Test
	public void distancias2Test() {
		Grafo grafo = new Grafo(5);
		grafoDistancias(grafo);
		assertEquals(2, (int) grafo.distancia(0, 3));
		assertEquals(2, (int) grafo.distancia(0, 4));
	}

	@Test
	public void distancias2ConOtroInicioTest() {
		Grafo grafo = new Grafo(5);
		grafoDistancias(grafo);
		assertEquals(2, (int) grafo.distancia(4, 0));
		assertEquals(2, (int) grafo.distancia(0, 4));
	}

	private void grafoNoConexo(Grafo grafo) {
		grafo.agregarArista(0, 1, 1);
		grafo.agregarArista(0, 2, 1);
		grafo.agregarArista(1, 2, 1);
		grafo.agregarArista(2, 3, 1);
		grafo.agregarArista(3, 4, 1);
	}

	private Grafo inicializarGrafo() {
		Grafo grafo = new Grafo(5);
		grafo.agregarArista(0, 1, 1);
		grafo.agregarArista(0, 2, 1);
		grafo.agregarArista(1, 3, 1);
		grafo.agregarArista(2, 4, 1);
		grafo.agregarArista(3, 4, 1);
		return grafo;
	}

	private void inicializarGrafo2(Grafo grafo) {
		grafo.agregarArista(0, 1, 1);
		grafo.agregarArista(0, 2, 1);
		grafo.agregarArista(1, 2, 1);
		grafo.agregarArista(1, 3, 1);
		grafo.agregarArista(1, 4, 1);
		grafo.agregarArista(2, 4, 1);
		grafo.agregarArista(3, 4, 1);
	}

	private void inicializarGrafo3(Grafo grafo) {
		grafo.agregarArista(0, 1, 1);
		grafo.agregarArista(0, 2, 1);
		grafo.agregarArista(1, 2, 1);
		grafo.agregarArista(1, 3, 1);
		grafo.agregarArista(1, 4, 1);
		grafo.agregarArista(2, 4, 1);
		grafo.agregarArista(2, 5, 1);
		grafo.agregarArista(3, 4, 1);
		grafo.agregarArista(4, 5, 1);
	}

	private void grafoDistancias(Grafo grafo) {
		grafo.agregarArista(0, 1, 1);
		grafo.agregarArista(0, 2, 1);
		grafo.agregarArista(1, 2, 1);
		grafo.agregarArista(1, 3, 1);
		grafo.agregarArista(2, 4, 1);
		grafo.agregarArista(3, 4, 1);
	}

	private Grafo grafoPizza() {
		Grafo grafo = new Grafo(9);
		grafo.agregarArista(0, 1, 1);
		grafo.agregarArista(0, 2, 1);
		grafo.agregarArista(0, 3, 1);
		grafo.agregarArista(0, 4, 1);
		grafo.agregarArista(0, 5, 1);
		grafo.agregarArista(0, 6, 1);
		grafo.agregarArista(0, 7, 1);
		grafo.agregarArista(0, 8, 1);
		grafo.agregarArista(1, 2, 1);
		grafo.agregarArista(2, 3, 1);
		grafo.agregarArista(3, 4, 1);
		grafo.agregarArista(4, 5, 1);
		grafo.agregarArista(5, 6, 1);
		grafo.agregarArista(6, 7, 1);
		grafo.agregarArista(7, 8, 1);
		grafo.agregarArista(8, 1, 1);
		return grafo;
	}
}
