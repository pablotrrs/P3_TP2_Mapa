package tests;

import static org.junit.Assert.*;
import java.io.IOException;
import org.junit.Test;
import tp.Negocio;
import tp.Point;
import tp.SolverPoligono;

public class SolverPoligonoTest {

	@Test(expected = NullPointerException.class)
	public void testPoligonoNull() throws IOException {
		assertTrue(SolverPoligono.puntoEstaContenidoEnPoligono(null, 10, new Point(1, 1)));
	}

	@Test(expected = NullPointerException.class)
	public void testPuntoNull() throws IOException {
		Point[] puntos = Negocio.toArray("buenos aires");

		assertTrue(SolverPoligono.puntoEstaContenidoEnPoligono(puntos, puntos.length, null));
	}

	@Test
	public void testPuntoContenido() throws IOException {
		Point[] puntos = Negocio.toArray("buenos aires");
		Point punto = new Point(-36.456636011596196, -60.1611328125);

		assertTrue(SolverPoligono.puntoEstaContenidoEnPoligono(puntos, puntos.length, punto));
	}

	@Test
	public void testPuntoNoContenido() throws IOException {
		Point[] puntos = Negocio.toArray("buenos aires");
		Point punto = new Point(-31.84023266790935, -63.896484375); // Punto en Cordoba

		assertFalse(SolverPoligono.puntoEstaContenidoEnPoligono(puntos, puntos.length, punto));
	}

	@Test
	public void testPuntoBorde() throws IOException {
		Point[] puntos = Negocio.toArray("buenos aires");
		Point punto = new Point(-40.838229652227874,-62.940673828125);

		assertTrue(SolverPoligono.puntoEstaContenidoEnPoligono(puntos, puntos.length, punto));
	}
}
