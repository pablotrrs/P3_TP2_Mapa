package tests;

import static org.junit.Assert.*;
import org.junit.Test;

import grafo.Grafo;
import grafo.Prim;

public class PrimTest {

	@Test(expected = IllegalArgumentException.class)
	public void grafoNoConexoTest() {
		Grafo grafo = new Grafo(5);
		grafo.agregarArista(0, 1, 1);
		grafo.agregarArista(2, 3, 1);

		Prim.calcular(grafo);
	}

	@Test
	public void AGMTest1() {
		Grafo grafo = grafoVideo();

		String esperado = "0 = {1(4.0)} 0 = {2(8.0)} 2 = {5(1.0)} 5 = {7(3.0)} 7 = {3(4.0)} 3 = {4(3.0)} 3 = {6(6.0)} 6 = {8(9.0)} .";
		String obtenido = Prim.calcular(grafo).toString();
		assertEquals(esperado, obtenido);
	}

	@Test
	public void AGMTestPracticaA() {
		Grafo grafo = grafoA();

		String esperado = "0 = {3(2.0)} 0 = {2(5.0)} 3 = {1(8.0)} 2 = {5(8.0)} 5 = {4(1.0)} 4 = {7(5.0)} 7 = {6(0.0)} 7 = {8(6.0)} .";
		String obtenido = Prim.calcular(grafo).toString();

		assertEquals(esperado, obtenido);
	}

	@Test
	public void AGMTestPracticaB() {
		Grafo grafo = grafoB();

		String esperado = "0 = {1(20.0)} 1 = {2(7.0)} 1 = {5(9.0)} 5 = {8(4.0)} 5 = {4(11.0)} 4 = {6(12.0)} 4 = {7(15.0)} 4 = {3(23.0)} .";
		String obtenido = Prim.calcular(grafo).toString();
		assertEquals(esperado, obtenido);
	}

	private Grafo grafoVideo() {
		Grafo grafo = new Grafo(9);
		grafo.agregarArista(0, 1, 4);
		grafo.agregarArista(0, 2, 8);
		grafo.agregarArista(1, 2, 12);
		grafo.agregarArista(1, 3, 8);
		grafo.agregarArista(2, 4, 6);
		grafo.agregarArista(2, 5, 1);
		grafo.agregarArista(3, 4, 3);
		grafo.agregarArista(3, 6, 6);
		grafo.agregarArista(3, 7, 4);
		grafo.agregarArista(4, 5, 5);
		grafo.agregarArista(5, 7, 3);
		grafo.agregarArista(6, 7, 13);
		grafo.agregarArista(6, 8, 9);
		grafo.agregarArista(7, 8, 10);
		return grafo;
	}

	private Grafo grafoA() {
		Grafo grafo = new Grafo(9);
		grafo.agregarArista(0, 2, 5);
		grafo.agregarArista(0, 1, 14);
		grafo.agregarArista(0, 3, 2);
		grafo.agregarArista(2, 1, 9);
		grafo.agregarArista(1, 3, 8);
		grafo.agregarArista(2, 4, 13);
		grafo.agregarArista(1, 4, 15);
		grafo.agregarArista(3, 4, 10);
		grafo.agregarArista(2, 5, 8);
		grafo.agregarArista(3, 7, 11);
		grafo.agregarArista(8, 5, 11);
		grafo.agregarArista(8, 6, 12);
		grafo.agregarArista(8, 7, 6);
		grafo.agregarArista(5, 6, 10);
		grafo.agregarArista(6, 7, 0);
		grafo.agregarArista(5, 4, 1);
		grafo.agregarArista(6, 4, 7);
		grafo.agregarArista(7, 4, 5);
		return grafo;
	}

	private Grafo grafoB() {
		Grafo grafo = new Grafo(9);
		grafo.agregarArista(0, 1, 20);
		grafo.agregarArista(1, 2, 7);
		grafo.agregarArista(3, 4, 23);
		grafo.agregarArista(4, 5, 11);
		grafo.agregarArista(6, 7, 22);
		grafo.agregarArista(7, 8, 20);
		grafo.agregarArista(0, 3, 35);
		grafo.agregarArista(3, 6, 25);
		grafo.agregarArista(1, 4, 18);
		grafo.agregarArista(4, 7, 15);
		grafo.agregarArista(2, 5, 20);
		grafo.agregarArista(5, 8, 4);
		grafo.agregarArista(1, 5, 9);
		grafo.agregarArista(4, 6, 12);
		grafo.agregarArista(0, 4, 30);
		grafo.agregarArista(4, 8, 25);
		return grafo;
	}
}
