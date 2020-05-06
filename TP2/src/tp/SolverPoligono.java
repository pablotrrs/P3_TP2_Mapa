package tp;

public class SolverPoligono {
	public static int INF = 10000;

	// Dados tres puntos colineares p, q, r,
	// la funcion chequea si el punto q esta sobre el
	// segmento linear 'pr'
	public static boolean enSegmento(Point p, Point q, Point r) {
		if (q.x <= Math.max(p.x, r.x) && q.x >= Math.min(p.x, r.x) && q.y <= Math.max(p.y, r.y)
				&& q.y >= Math.min(p.y, r.y)) {
			return true;
		}
		return false;
	}

	// Para encontrar la orientacion de la tripleta ordenada (p, q, r).
	// La funcion retorna los siguientes valores
	// 0 --> p, q y r son colineares
	// 1 --> En sentido de las agujas del reloj
	// 2 --> En sentido contrario de las agujas del reloj
	public static int orientacion(Point p, Point q, Point r) {
		double valor = (q.y - p.y) * (r.x - q.x) - (q.x - p.x) * (r.y - q.y);

		if (valor == 0) {
			return 0; // colinear
		}
		return (valor > 0) ? 1 : 2; // En sentido de las agujas del reloj o contrario
	}

	// La funcion que retorna true si
	// El segmento linear 'p1q1' y 'p2q2' intersectan.
	public static boolean intersectar(Point p1, Point q1, Point p2, Point q2) {
		// Encuentra las cuatro orientaciones necesarias para casos especiales y
		// generales
		int o1 = orientacion(p1, q1, p2);
		int o2 = orientacion(p1, q1, q2);
		int o3 = orientacion(p2, q2, p1);
		int o4 = orientacion(p2, q2, q1);

		// Caso general
		if (o1 != o2 && o3 != o4) {
			return true;
		}

		// Casos especiales
		// p1, q1 y p2 son colineares y
		// p2 esta posado sobre el segmento p1q1
		if (o1 == 0 && enSegmento(p1, p2, q1)) {
			return true;
		}

		// p1, q1 y p2 son colineares y
		// q2 esta posado sobre el segmento p1q1
		if (o2 == 0 && enSegmento(p1, q2, q1)) {
			return true;
		}

		// p2, q2 y p1 son colineares y
		// p1 esta posado sobre el segmento p2q2
		if (o3 == 0 && enSegmento(p2, p1, q2)) {
			return true;
		}

		// p2, q2 y q1 son colineares y
		// q1 esta posado sobre el segmento p2q2
		if (o4 == 0 && enSegmento(p2, q1, q2)) {
			return true;
		}

		return false;
	}

	// Retorna true si el punto p se encuentra
	// dentro de polygon[] con n vertices
	public static boolean puntoEstaContenidoEnPoligono(Point polygon[], int n, Point p) {
		if(polygon == null || p == null) {
			throw new NullPointerException("El poligono y el punto no pueden ser null!");
		}
		
		// Tienen que haber al menos tres vertices en polygon[]
		if (n < 3) {
			return false;
		}

		// Crea un punto para el segmento linear desde p hasta infinito
		Point extremo = new Point(INF, p.y);

		// Cuenta las intersecciones de la linea de arriba
		// con los lados del poligono
		int count = 0, i = 0;
		do {
			int next = (i + 1) % n;
			// Chequea si el segmento linear desde 'p' hasta
			// 'extremo' intersecta con el segmento linear desde
			// 'polygon[i]' a 'polygon[next]'
			if (intersectar(polygon[i], polygon[next], p, extremo)) {
				// Si el punto 'p' es colinear con el segmento
				// linear 'i-next', luego chequea si este se encuentra
				// sobre el segmento. Si lo hace, retorna true sino falso.
				if (orientacion(polygon[i], p, polygon[next]) == 0) {
					return enSegmento(polygon[i], p, polygon[next]);
				}

				count++;
			}
			i = next;
		} while (i != 0);

		// Retorna true si la cuenta es impar sino falso
		return (count % 2 == 1);
	}
}
