package tp;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Toolkit;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;
import java.util.ArrayList;
import java.util.Map;
import java.util.TreeMap;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import org.openstreetmap.gui.jmapviewer.Coordinate;
import org.openstreetmap.gui.jmapviewer.JMapViewer;
import org.openstreetmap.gui.jmapviewer.MapMarkerDot;
import org.openstreetmap.gui.jmapviewer.MapPolygonImpl;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.LineBorder;
import javax.swing.JLabel;
import javax.swing.JProgressBar;

import java.awt.Font;

public class Interfaz {

	private Integer xx = 0, yy = 0;

	private final Coordinate ARGENTINA = new Coordinate(-40.99400375757576, -64.41796875);
	private final Dimension resolucion = Toolkit.getDefaultToolkit().getScreenSize();

	private JFrame frame;
	private JMapViewer mapa;
	private JDialog frameCosto;
	private JDialog frameMarcar;

	private JButton btnMarcar;

	private JLabel lblAGM;
	private JLabel lblXMapa;
	private JLabel lblMarcar;
	private JLabel lblNombre;
	private JLabel lblAGMHija;
	private JLabel lblLatitud;
	private JLabel lblLongitud;
	private JLabel lblProvincia;
	private JLabel lblMarcarHija;
	private JLabel lblLimpiarMapa;
	private JLabel lblXFrameCosto;
	private JLabel lblCostoTitulo;
	private JLabel lblXFrameMarcar;
	private JLabel lblCostoEnNumeros;
	private JLabel lblRecuadroMapa;
	private JLabel lblMinimizarMapa;
	private JLabel lblLimpiarMapaHija;
	private JLabel lblRecuadroFrameCosto;
	private JLabel lblRecuadroFrameMarcar;
	private JLabel lblPosicionarEnArgentina;
	private JLabel lblPosicionarEnArgentinaHija;

	private JTextField txfNombre;
	private JTextField txfLatitud;
	private JTextField txfLongitud;
	private JTextField txfProvincia;

	private static boolean apretoAGM;
	private static ArrayList<JLabel> varsMapa;
	private static ArrayList<JTextField> textFields;
	protected static ArrayList<MapMarkerDot> marcadores;
	protected static ArrayList<Tupla<String, Coordinate>> puntosMarcados;

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Interfaz window = new Interfaz();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public Interfaz() {
		inicializar();
	}

	private void inicializar() {
		inicializarVariables();
		frames();
		botones();
		textFields();
		labels();
		eventos();
	}

	private void inicializarVariables() {
		puntosMarcados = new ArrayList<Tupla<String, Coordinate>>();
		marcadores = new ArrayList<MapMarkerDot>();
		textFields = new ArrayList<JTextField>();
	}

	@SuppressWarnings("deprecation")
	private void frames() {
		ArrayList<JDialog> framesExtras = new ArrayList<JDialog>();

		frame = new JFrame();
		frame.setBounds(0, 0, resolucion.width, resolucion.height);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setExtendedState(JFrame.MAXIMIZED_BOTH);
		frame.setUndecorated(true);
		frame.getContentPane().setLayout(null);
		frame.setIconImage(Toolkit.getDefaultToolkit().getImage(Interfaz.class.getResource("/tp/Imgs/icono.png")));

		mapa = new JMapViewer();
		mapa.setBounds(0, 0, resolucion.width, resolucion.height);
		mapa.setDisplayPosition(ARGENTINA, 5);
		mapa.setZoomContolsVisible(false);
		mapa.setLayout(null);
		frame.getContentPane().add(mapa);

		frameCosto = new JDialog(frame, "");
		framesExtras.add(frameCosto);

		frameMarcar = new JDialog(frame, "");
		framesExtras.add(frameMarcar);

		configurarFrames(framesExtras);
	}

	private void botones() {
		btnMarcar = new JButton("Marcar");
		btnMarcar.setBounds(268, 105, 157, 23);
		btnMarcar.setFont(new Font("Quicksand Medium", Font.BOLD, 18));
		btnMarcar.setOpaque(false);
		btnMarcar.setForeground(Color.WHITE);
		btnMarcar.setBackground(Color.BLACK);
		btnMarcar.setBorder(new LineBorder(new Color(255, 255, 255), 2, true));
		frameMarcar.getContentPane().add(btnMarcar);
	}

	private void textFields() {
		final int ESPACIO = 157;

		txfNombre = new JTextField();
		txfProvincia = new JTextField();
		txfLatitud = new JTextField();
		txfLongitud = new JTextField();

		textFields.add(txfNombre);
		textFields.add(txfProvincia);
		textFields.add(txfLatitud);
		textFields.add(txfLongitud);

		configurarTextFields(ESPACIO);
	}

	private void labels() {
		Map<String, ArrayList<JLabel>> labels = new TreeMap<String, ArrayList<JLabel>>();

		varsMapa = new ArrayList<JLabel>();
		ArrayList<JLabel> etiquetas = new ArrayList<JLabel>();
		ArrayList<JLabel> varsFrameCosto = new ArrayList<JLabel>();
		ArrayList<JLabel> varsFrameMarcar = new ArrayList<JLabel>();

		// *************************FrameMarcar*******************************//

		lblXFrameMarcar = new JLabel("X");
		lblXFrameMarcar.setBounds(665, 2, 48, 14);
		varsFrameMarcar.add(lblXFrameMarcar);
		etiquetas.add(lblXFrameMarcar);

		lblRecuadroFrameMarcar = new JLabel("");
		lblRecuadroFrameMarcar.setBounds(0, 0, 700, 20);
		varsFrameMarcar.add(lblRecuadroFrameMarcar);
		etiquetas.add(lblRecuadroFrameMarcar);

		lblNombre = new JLabel("Nombre del marcador");
		varsFrameMarcar.add(lblNombre);
		etiquetas.add(lblNombre);

		lblProvincia = new JLabel("Provincia");
		varsFrameMarcar.add(lblProvincia);
		etiquetas.add(lblProvincia);

		lblLongitud = new JLabel("Longitud");
		varsFrameMarcar.add(lblLongitud);
		etiquetas.add(lblLongitud);

		lblLatitud = new JLabel("Latitud");
		varsFrameMarcar.add(lblLatitud);
		etiquetas.add(lblLatitud);

		// label vacia agregada porque por alguna razon la ultima label no se ajusta a
		// lo indicado (arregla bug)
		varsFrameMarcar.add(new JLabel());

		// *******************************Mapa*********************************//

		lblXMapa = new JLabel("X");
		lblXMapa.setBounds(1890, 2, 26, 14);
		varsMapa.add(lblXMapa);
		etiquetas.add(lblXMapa);

		lblMinimizarMapa = new JLabel("---");
		lblMinimizarMapa.setBounds(1865, 2, 26, 14);
		varsMapa.add(lblMinimizarMapa);
		etiquetas.add(lblMinimizarMapa);

		lblRecuadroMapa = new JLabel("");
		lblRecuadroMapa.setBounds(0, 0, 1920, 20);
		varsMapa.add(lblRecuadroMapa);

		lblMarcar = new JLabel("ADL");
		varsMapa.add(lblMarcar);

		lblMarcarHija = new JLabel("ANADIR LOCALIDAD");
		lblMarcarHija.setBounds(1675, 887, 180, 31);
		varsMapa.add(lblMarcarHija);

		lblLimpiarMapa = new JLabel("LPM");
		varsMapa.add(lblLimpiarMapa);

		lblLimpiarMapaHija = new JLabel("LIMPIAR MAPA");
		lblLimpiarMapaHija.setBounds(1731, 929, 104, 31);
		varsMapa.add(lblLimpiarMapaHija);

		lblPosicionarEnArgentina = new JLabel("ARG");
		varsMapa.add(lblPosicionarEnArgentina);

		lblPosicionarEnArgentinaHija = new JLabel("REESTABLECER POSICION EN ARGENTINA");
		lblPosicionarEnArgentinaHija.setBounds(1563, 971, 268, 31);
		varsMapa.add(lblPosicionarEnArgentinaHija);

		lblAGM = new JLabel("AGM");
		varsMapa.add(lblAGM);

		lblAGMHija = new JLabel("GENERAR CONEXION MAS BARATA");
		lblAGMHija.setBounds(1602, 1013, 233, 31);
		varsMapa.add(lblAGMHija);

		// *******************************FrameCosto***************************************//

		lblXFrameCosto = new JLabel("X");
		lblXFrameCosto.setBounds(314, 2, 48, 14);
		varsFrameCosto.add(lblXFrameCosto);
		etiquetas.add(lblXFrameCosto);

		lblRecuadroFrameCosto = new JLabel("");
		lblRecuadroFrameCosto.setBounds(0, 0, 350, 20);
		varsFrameCosto.add(lblRecuadroFrameCosto);

		lblCostoTitulo = new JLabel("costo");
		varsFrameCosto.add(lblCostoTitulo);
		etiquetas.add(lblCostoTitulo);

		lblCostoEnNumeros = new JLabel("0");
		varsFrameCosto.add(lblCostoEnNumeros);
		etiquetas.add(lblCostoEnNumeros);

		varsFrameCosto.add(new JLabel());

		// **************Configuracion de las labels******************//

		labels.put("labels", etiquetas);
		labels.put("mapa", varsMapa);
		labels.put("frameMarcar", varsFrameMarcar);
		labels.put("frameCosto", varsFrameCosto);

		configurarEtiquetas(labels);
	}

	// ********************************************************************************************************//

	private void eventos() {
		interaccionBotonMarcar();
		interaccionMapa();
		interaccionFrames();
	}

	private void interaccionBotonMarcar() {
		btnMarcar.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if (apretoAGM)
					limpiarMapa();

				if (inputDeUsuarioEsValido()) {

					Coordinate coordenada = new Coordinate(Double.valueOf(txfLatitud.getText()),
							Double.valueOf(txfLongitud.getText()));
					marcarPunto(coordenada);
				}
				vaciarTextFields();
			}
		});
	}

	private boolean inputDeUsuarioEsValido() {
		return !txfNombre.getText().equals("") && !txfProvincia.getText().equals("") && !txfLatitud.getText().equals("")
				&& !txfLongitud.getText().equals("") && coordenadaEsValida()
				&& !Tupla.contiene(puntosMarcados,
						new Tupla<String, Coordinate>(txfProvincia.getText(), new Coordinate(
								Double.valueOf(txfLatitud.getText()), Double.valueOf(txfLongitud.getText()))))
				&& marcadores.size() < 24;
	}

	// Creamos otro thread que se encargue de marcar el punto ingresado por el
	// usuario
	private void marcarPunto(Coordinate coordenada) {
		try {
			if (SW.cantThreadsActivos == 0) {
				JLabel mensajeSW = new JLabel("marcando");
				mensajeSW.setBounds(900, 400, 500, 200);
				mensajeSW.setForeground(new Color(178, 34, 34));
				mensajeSW.setFont(new Font("Quicksand Medium", Font.BOLD, 25));
				mensajeSW.setVisible(false);
				mapa.add(mensajeSW);

				JProgressBar progressBar = new JProgressBar();
				progressBar.setBounds(820, 525, 300, 18);
				progressBar.setForeground(new Color(178, 34, 34));
				mapa.add(progressBar);

				SW sw = new SW(mensajeSW, progressBar, txfProvincia.getText(), coordenada, mapa, txfNombre.getText());
				sw.execute();
			}
		} catch (Exception e) {
		}
	}

	// ********************************************************************************************************//

	private void interaccionMapa() {
		for (int i = 3; i < varsMapa.size() - 1; i += 2) {
			JLabel etiqueta = varsMapa.get(i);
			JLabel etiquetaHija = varsMapa.get(i + 1);

			etiqueta.addMouseListener(new MouseAdapter() {
				@Override
				public void mouseClicked(MouseEvent e) {

					if (etiqueta.getText().equalsIgnoreCase("ADL")) {
						frameMarcar.setVisible(true);

					} else if (etiqueta.getText().equalsIgnoreCase("LPM")) {
						limpiarMapa();
						eliminarMarcadoresAntiguos();

					} else if (etiqueta.getText().equalsIgnoreCase("ARG")) {
						mapa.setDisplayPosition(ARGENTINA, 5);

					} else if (etiqueta.getText().equalsIgnoreCase("AGM")) {
						if (puntosMarcados.size() > 1) {
							generarConexionMasBarataYDibujar();
						}
					}
				}

				@Override
				public void mouseEntered(MouseEvent e) {
					etiquetaHija.setVisible(true);
				}

				@Override
				public void mouseExited(MouseEvent e) {
					etiquetaHija.setVisible(false);
				}
			});
		}
	}

	// Genera el AGM una vez el usuario indico hacerlo
	private void generarConexionMasBarataYDibujar() {
		ArrayList<MapPolygonImpl> poligonosGrafo = Negocio.generarAGM(puntosMarcados);
		limpiarMapa();
		agregarPoligonos(poligonosGrafo);
		agregarMarcadores();
		eliminarMarcadoresAntiguos();
		lblCostoEnNumeros.setText("$" + String.valueOf(Math.round(Negocio.costoAGM)));
		frameCosto.setVisible(true);
	}

	// ********************************************************************************************************//

	private void interaccionFrames() {
		ArrayList<JLabel> etiquetas = new ArrayList<JLabel>();
		etiquetas.add(lblXMapa);
		etiquetas.add(lblMinimizarMapa);
		etiquetas.add(lblXFrameMarcar);
		etiquetas.add(lblXFrameCosto);

		for (JLabel etiqueta : etiquetas) {
			etiqueta.addMouseListener(new MouseAdapter() {
				@Override
				public void mouseClicked(MouseEvent e) {
					if (etiqueta.getParent().getClass() == JMapViewer.class) {
						if (etiqueta.getText().equalsIgnoreCase("X")) {
							System.exit(1);
						} else {
							frame.setExtendedState(JFrame.HIDE_ON_CLOSE);
						}
					} else {
						if (etiqueta.getX() > 400) {
							frameMarcar.setVisible(false);
							vaciarTextFields();
						} else {
							frameCosto.setVisible(false);
							lblCostoEnNumeros.setText("");
						}
					}
				}
			});
		}

		ArrayList<JLabel> recuadros = new ArrayList<JLabel>();
		recuadros.add(lblRecuadroMapa);
		recuadros.add(lblRecuadroFrameMarcar);
		recuadros.add(lblRecuadroFrameCosto);

		lblRecuadroMapa.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if (e.getClickCount() == 2) {
					frame.setBounds(0, 0, resolucion.width, resolucion.height);
				}
			}
		});

		for (JLabel recuadro : recuadros) {
			recuadro.addMouseListener(new MouseAdapter() {
				@Override
				public void mousePressed(MouseEvent e) {
					xx = e.getX();
					yy = e.getY();
				}

			});

			recuadro.addMouseMotionListener(new MouseMotionAdapter() {
				@Override
				public void mouseDragged(MouseEvent ev) {
					int x = ev.getXOnScreen();
					int y = ev.getYOnScreen();

					if (recuadro.getParent().getClass() == JMapViewer.class) {
						frame.setBounds(x - xx, y - yy, resolucion.width, resolucion.height);
					} else {
						if (recuadro.getWidth() > 400) {
							frameMarcar.setBounds(x - xx, y - yy, 700, 150);
						} else {
							frameCosto.setBounds(x - xx, y - yy, 350, 150);
						}
					}
				}
			});
		}
	}

	// ********************************************************************************************************//

	private void configurarFrames(ArrayList<JDialog> framesExtras) {
		int resolucionXFrame = 350;

		for (JDialog frameExtra : framesExtras) {
			frameExtra.getContentPane().setBackground(new Color(178, 34, 34));
			frameExtra.setBounds(0, 0, resolucionXFrame, 150);
			resolucionXFrame *= 2;
			frameExtra.setUndecorated(true);
			frameExtra.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
			frameExtra.setLocationRelativeTo(null);
		}
	}

	private void configurarTextFields(final int ESPACIO) {
		int multiplicando = 0;

		for (JTextField textField : textFields) {
			textField.setBounds(20 + ESPACIO * multiplicando + 10 * multiplicando, 67, 157, 20);
			textField.setColumns(10);
			textField.setHorizontalAlignment(SwingConstants.CENTER);
			frameMarcar.getContentPane().add(textField);
			multiplicando++;
		}
	}

	private void configurarEtiquetas(Map<String, ArrayList<JLabel>> etiquetas) {
		int posYLabelMapa = 887;
		int posYLabelFrameCosto = 30;
		int posXLabelFrameMarcar = 30;

		for (Map.Entry<String, ArrayList<JLabel>> lista : etiquetas.entrySet()) {
			for (JLabel etiqueta : lista.getValue()) {
				if (lista.getKey().equalsIgnoreCase("labels")) {
					etiqueta.setForeground(Color.WHITE);
					etiqueta.setHorizontalAlignment(SwingConstants.CENTER);
					if (etiqueta.getText().equalsIgnoreCase("X")) {
						etiqueta.setFont(new Font("Quicksand Medium", Font.BOLD, 18));
					}
					if (etiqueta.getText().equalsIgnoreCase("---")) {
						etiqueta.setFont(new Font("Harlow Solid Italic", Font.BOLD, 18));
					}
				}

				if (lista.getKey().equalsIgnoreCase("mapa")) {
					etiqueta.setHorizontalAlignment(SwingConstants.CENTER);
					if (etiqueta.getText().equalsIgnoreCase("")) {
						etiqueta.setOpaque(true);
					}
					if (!etiqueta.getText().equalsIgnoreCase("---") && !etiqueta.getText().equalsIgnoreCase("X")) {
						etiqueta.setForeground(new Color(178, 34, 34));
						etiqueta.setBackground(new Color(178, 34, 34));
					}
					if (etiqueta.getText().equalsIgnoreCase("ADL") || etiqueta.getText().equalsIgnoreCase("LPM")
							|| etiqueta.getText().equalsIgnoreCase("ARG")
							|| etiqueta.getText().equalsIgnoreCase("AGM")) {
						etiqueta.setBounds(1831, posYLabelMapa, 79, 31);
						posYLabelMapa += 42;
						etiqueta.setFont(new Font("Quicksand Medium", Font.BOLD, 25));
					} else if (!etiqueta.getText().equalsIgnoreCase("---") && !etiqueta.getText().equalsIgnoreCase("X")
							&& !etiqueta.getText().equalsIgnoreCase("")) {
						etiqueta.setFont(new Font("Quicksand Medium", Font.BOLD, 12));
						etiqueta.setVisible(false);
					}
					mapa.add(etiqueta);
				}

				if (lista.getKey().equalsIgnoreCase("frameMarcar")) {
					etiqueta.setFont(new Font("Quicksand Medium", Font.BOLD, 12));
					if (!etiqueta.getText().equalsIgnoreCase("X") && !etiqueta.getText().equalsIgnoreCase("")) {
						etiqueta.setBounds(posXLabelFrameMarcar, 43, 157, 14);
						posXLabelFrameMarcar += 172;
						posXLabelFrameMarcar -= 5;
					}
					frameMarcar.getContentPane().add(etiqueta);
				}

				if (lista.getKey().equalsIgnoreCase("frameCosto")) {
					if (!etiqueta.getText().equalsIgnoreCase("X") && !etiqueta.getText().equalsIgnoreCase("")) {
						etiqueta.setFont(new Font("Quicksand Medium", Font.BOLD, 25));
						etiqueta.setBounds(95, posYLabelFrameCosto, 180, 35);
						posYLabelFrameCosto *= 2;
					}
					frameCosto.getContentPane().add(etiqueta);
				}

				if (etiqueta.getText().equalsIgnoreCase("")) {
					etiqueta.setBorder(new LineBorder(Color.WHITE, 2, true));
				}
			}
		}
	}

	// Restringimos a que los inputs en latitud y longitud sean solamente numeros

	private boolean coordenadaEsValida() {
		try {
			Double.parseDouble(txfLatitud.getText());
			Double.parseDouble(txfLongitud.getText());
			return true;
		} catch (NumberFormatException e) {
			return false;
		}
	}

	private void limpiarMapa() {
		apretoAGM = false;
		mapa.removeAllMapMarkers();
		mapa.removeAllMapPolygons();
		puntosMarcados = new ArrayList<Tupla<String, Coordinate>>();
	}

	// Utilizado cuando el usuario indico generar el AGM
	private void eliminarMarcadoresAntiguos() {
		apretoAGM = true;
		marcadores = new ArrayList<MapMarkerDot>();
	}

	// Dibujamos las conexiones de los puntos en el mapa
	private void agregarPoligonos(ArrayList<MapPolygonImpl> poligonos) {
		for (MapPolygonImpl poligono : poligonos) {
			cambiarColorPoligono(poligono);
			mapa.addMapPolygon(poligono);
		}
	}

	private void cambiarColorPoligono(MapPolygonImpl poligono) {
		poligono.setColor(Color.RED);
		poligono.setBackColor(null);
		poligono.setStroke(new BasicStroke(4));
	}

	private void agregarMarcadores() {
		for (int i = 0; i < marcadores.size(); i++) {
			cambiarColorMarcador(marcadores.get(i));
			mapa.addMapMarker(marcadores.get(i));
		}
	}

	private void cambiarColorMarcador(MapMarkerDot marca) {
		marca.setColor(Color.RED);
		marca.setBackColor(Color.RED);
	}

	private void vaciarTextFields() {
		for (JTextField textField : textFields) {
			textField.setText("");
		}
	}
}
