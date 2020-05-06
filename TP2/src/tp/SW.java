package tp;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.concurrent.ExecutionException;
import javax.swing.JLabel;
import javax.swing.JProgressBar;
import javax.swing.SwingWorker;
import org.openstreetmap.gui.jmapviewer.Coordinate;
import org.openstreetmap.gui.jmapviewer.JMapViewer;
import org.openstreetmap.gui.jmapviewer.MapMarkerDot;
import org.openstreetmap.gui.jmapviewer.MapPolygonImpl;

public class SW extends SwingWorker<Boolean, Boolean> {
	protected static int cantThreadsActivos = 0;

	private JMapViewer mapa;
	private String provincia;
	private JLabel mensajeSW;
	private String nombreMarca;
	private Coordinate coordenada;
	private JProgressBar barraProgreso;

	public SW(JLabel mensajeSW, JProgressBar barra, String provincia, Coordinate coordenada, JMapViewer mapa,
			String nombreMarca) {

		cantThreadsActivos++;
		this.mapa = mapa;
		this.provincia = provincia;
		this.mensajeSW = mensajeSW;
		this.nombreMarca = nombreMarca;
		this.coordenada = coordenada;
		this.barraProgreso = barra;
	}

	@Override
	protected Boolean doInBackground() throws Exception {
		mensajeSW.setVisible(true);
		barraProgreso.setVisible(true);
		barraProgreso.setIndeterminate(true);

		boolean ret = false;
		Point[] puntos = Negocio.toArray(provincia);
		if (puntos != null)
			ret = SolverPoligono.puntoEstaContenidoEnPoligono(puntos, puntos.length,
					new Point(coordenada.getLat(), coordenada.getLon()));

		return ret;
	}

	@Override
	public void done() {
		try {
			if (get()) {
				mensajeSW.setBounds(870, 400, 500, 200);
				mensajeSW.setText("marcado con exito");
				almacenarPuntoYDibujar();
			} else {
				mensajeSW.setText("no marcado");
			}

			barraProgreso.setIndeterminate(false);
			barraProgreso.setVisible(false);
			cantThreadsActivos--;

			desvanecerMensaje();

		} catch (InterruptedException ex) {
			mensajeSW.setText("Interrumpido mientras esperaba resultado.");
		} catch (ExecutionException ex) {
			mensajeSW.setText("Error mientras se comprobaba el punto.");
		}
	}

	private void desvanecerMensaje() {
		ActionListener timer = new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				mensajeSW.setVisible(false);
			}
		};
		javax.swing.Timer tick = new javax.swing.Timer(1000, timer);
		tick.setRepeats(false);
		tick.start();
	}

	private void almacenarPuntoYDibujar() {
		Interfaz.puntosMarcados.add(new Tupla<String, Coordinate>(provincia, coordenada));
		
		MapMarkerDot marca = new MapMarkerDot(nombreMarca, coordenada);
		cambiarColorMarcador(marca);
		mapa.addMapMarker(marca);
		
		MapPolygonImpl poligono = conectarCompleto(Interfaz.puntosMarcados);
		cambiarColorPoligono(poligono);
		mapa.addMapPolygon(poligono);
		
		Interfaz.marcadores.add(marca);
	}

		// Conecta a todos los puntos y forma un poligono completo
	public MapPolygonImpl conectarCompleto(ArrayList<Tupla<String, Coordinate>> localidades) {
		ArrayList<Coordinate> poligonoCompleto = new ArrayList<Coordinate>();
		for (Tupla<String, Coordinate> coordenada1 : localidades) {
			poligonoCompleto.add(coordenada1.getSegundoElemento());
			for (Tupla<String, Coordinate> coordenada2 : localidades) {
				if (!coordenada1.equals(coordenada2)) {
					poligonoCompleto.add(coordenada2.getSegundoElemento());
				}
			}
		}
		return new MapPolygonImpl(poligonoCompleto);
	}

	private void cambiarColorMarcador(MapMarkerDot marca) {
		marca.setColor(Color.RED);
		marca.setBackColor(Color.RED);
	}

	private void cambiarColorPoligono(MapPolygonImpl poligono) {
		poligono.setColor(Color.RED);
		poligono.setBackColor(null);
		poligono.setStroke(new BasicStroke(4));
	}
}