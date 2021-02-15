//Jorge Antonio Toscano Lara
package PruebaC2;

	import java.awt.BorderLayout;
	import java.awt.Color;
	import java.awt.Font;
	import java.awt.GridLayout;
	import java.awt.event.MouseAdapter;
	import java.awt.event.MouseEvent;

	import javax.swing.JButton;
	import javax.swing.JFrame;
	import javax.swing.JPanel;
	import javax.swing.JTextField;
	import javax.swing.WindowConstants;
	import javax.swing.border.EmptyBorder;

	
	public class Calculadora extends JFrame {


		/** numero tecleado */
		JTextField pantalla;

		/** guarda el resultado de la operacion anterior o el número tecleado */
		double resultado;

		/** para guardar la operacion a realizar */
		String operacion;

		/** Los paneles donde colocaremos los botones */
		JPanel panelNumeros, panelOperaciones;

		/** Indica si estamos iniciando o no una operación */
		boolean nuevaOperacion = true;

		/**
		 * Constructor. Crea los botones y componentes de la calculadora
		 */
		public Calculadora() {
			super();
			setSize(450, 500);
			setTitle("Calculadora Básica");
			setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
			setResizable(true);

			// Vamos a dibujar sobre el panel
			JPanel panel = (JPanel) this.getContentPane();
			panel.setLayout(new BorderLayout());

			pantalla = new JTextField("0", 20);
			pantalla.setBorder(new EmptyBorder(4, 4, 4, 4));
			pantalla.setFont(new Font("AgencyFB", Font.BOLD, 70));
			pantalla.setHorizontalAlignment(JTextField.RIGHT);
			pantalla.setEditable(true);
			pantalla.setBackground(Color.gray);
			panel.add("North", pantalla);

			panelNumeros = new JPanel();
			panelNumeros.setLayout(new GridLayout(4, 3));
			panelNumeros.setFont(new Font("AgencyFb", Font.BOLD, 70));
			panelNumeros.setBorder(new EmptyBorder(4, 4, 4, 4));
			panelNumeros.setBackground(Color.gray);
			for (int n = 9; n >= 0; n--) {
				nuevoBotonNumerico("" + n);
			}

			nuevoBotonNumerico(".");

			panel.add("Center", panelNumeros);

			panelOperaciones = new JPanel();
			panelOperaciones.setLayout(new GridLayout(3, 2));
			panelOperaciones.setFont(new Font("AgencyFb", Font.BOLD, 70));
			panelOperaciones.setBorder(new EmptyBorder(4, 4, 4, 4));
			panelOperaciones.setBackground(Color.gray);
			
			nuevoBotonOperacion("+");
			nuevoBotonOperacion("CE");
			nuevoBotonOperacion("-");
			nuevoBotonOperacion("*");
			nuevoBotonOperacion("/");
			nuevoBotonOperacion("=");
			

			panel.add("East", panelOperaciones);

		}

		/*
		 Crea un boton del teclado numérico y enlaza sus eventos con el listener
		 correspondiente
		 */

		private void nuevoBotonNumerico(String digito) {
			JButton btn = new JButton();
			btn.setText(digito);
			btn.setForeground(Color.blue);
			btn.setBackground(Color.LIGHT_GRAY);
			btn.addMouseListener(new MouseAdapter() {

				@Override
				public void mouseReleased(MouseEvent evt) {
					JButton btn = (JButton) evt.getSource();
					numeroPulsado(btn.getText());
				}
			});

			panelNumeros.add(btn);
		}

		/*
		 * Crea un botón de operacion y lo enlaza con sus eventos.
		*/
		private void nuevoBotonOperacion(String operacion) {
			JButton btn = new JButton(operacion);
			btn.setForeground(Color.blue);
			btn.setBackground(Color.LIGHT_GRAY);
			btn.addMouseListener(new MouseAdapter() {

				@Override
				public void mouseReleased(MouseEvent evt) {
					JButton btn = (JButton) evt.getSource();
					operacionPulsado(btn.getText());
				}
			});

			panelOperaciones.add(btn);
		}

		/**
		 * Gestiona las pulsaciones de teclas numéricas
		 */
		private void numeroPulsado(String digito) {
			if (pantalla.getText().equals("0") || nuevaOperacion) {
				pantalla.setText(digito);
			} else {
				pantalla.setText(pantalla.getText() + digito);
			}
			nuevaOperacion = false;
		}

		/**
		 * Gestiona el gestiona las pulsaciones de teclas de operación
		 */
		private void operacionPulsado(String tecla) {
			if (tecla.equals("=")) {
				calcularResultado();
			} else if (tecla.equals("CE")) {
				resultado = 0;
				pantalla.setText("");
				nuevaOperacion = true;
			} else {
				operacion = tecla;
				if ((resultado > 0) && !nuevaOperacion) {
					calcularResultado();
				} else {
					resultado = new Double(pantalla.getText());
				}
			}

			nuevaOperacion = true;
		}

		/*
		Calcula el resultado y lo muestra por pantalla
		*/
		private void calcularResultado() {
			if (operacion.equals("+")) {
				resultado += new Double(pantalla.getText());
			} else if (operacion.equals("-")) {
				resultado -= new Double(pantalla.getText());
			} else if (operacion.equals("/")) {
				resultado /= new Double(pantalla.getText());
			} else if (operacion.equals("*")) {
				resultado *= new Double(pantalla.getText());
			}

			pantalla.setText("" + resultado);
			operacion = "";
		}
	}

