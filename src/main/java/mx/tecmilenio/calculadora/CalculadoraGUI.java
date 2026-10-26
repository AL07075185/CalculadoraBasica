package mx.tecmilenio.calculadora;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Insets;
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.util.Locale;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

/**
 * Interfaz grafica de la calculadora construida con JFrame.
 * HU-01 Interfaz para capturar dos numeros y elegir la operacion.
 * HU-04 Mensajes claros ante datos vacios, no numericos o division entre cero.
 */
public class CalculadoraGUI extends JFrame {

    private static final Color COLOR_PRIMARIO = new Color(31, 56, 100);
    private static final Color COLOR_FONDO_RESULTADO = new Color(232, 238, 247);

    private final Calculadora calculadora = new Calculadora();
    private final DecimalFormat formatoNumero =
            new DecimalFormat("0.######", DecimalFormatSymbols.getInstance(Locale.US));

    private JTextField txtNumeroUno;
    private JTextField txtNumeroDos;
    private JLabel lblResultado;
    private JTextArea txtHistorial;

    public CalculadoraGUI() {
        super("Calculadora Básica - Actividad 4");
        inicializarComponentes();
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setResizable(false);
        pack();
        setLocationRelativeTo(null);
    }

    private void inicializarComponentes() {
        JPanel panelPrincipal = new JPanel(new BorderLayout(0, 12));
        panelPrincipal.setBorder(BorderFactory.createEmptyBorder(16, 18, 16, 18));

        panelPrincipal.add(crearEncabezado(), BorderLayout.NORTH);

        JPanel panelCentro = new JPanel(new BorderLayout(0, 12));
        panelCentro.add(crearPanelCaptura(), BorderLayout.NORTH);
        panelCentro.add(crearPanelOperaciones(), BorderLayout.CENTER);
        panelCentro.add(crearPanelResultado(), BorderLayout.SOUTH);
        panelPrincipal.add(panelCentro, BorderLayout.CENTER);

        panelPrincipal.add(crearPanelHistorial(), BorderLayout.SOUTH);

        setContentPane(panelPrincipal);
    }

    private JPanel crearEncabezado() {
        JPanel panel = new JPanel(new GridLayout(2, 1));
        JLabel lblTitulo = new JLabel("Calculadora Básica", SwingConstants.CENTER);
        lblTitulo.setFont(new Font("SansSerif", Font.BOLD, 20));
        lblTitulo.setForeground(COLOR_PRIMARIO);
        panel.add(lblTitulo);
        return panel;
    }

    private JPanel crearPanelCaptura() {
        JPanel panel = new JPanel(new GridBagLayout());
        panel.setBorder(BorderFactory.createTitledBorder("Captura de datos"));
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(6, 8, 6, 8);
        gbc.fill = GridBagConstraints.HORIZONTAL;

        txtNumeroUno = new JTextField(14);
        txtNumeroDos = new JTextField(14);
        txtNumeroUno.setFont(new Font("SansSerif", Font.PLAIN, 15));
        txtNumeroDos.setFont(new Font("SansSerif", Font.PLAIN, 15));

        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.weightx = 0;
        panel.add(new JLabel("Primer número:"), gbc);
        gbc.gridx = 1;
        gbc.weightx = 1;
        panel.add(txtNumeroUno, gbc);

        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.weightx = 0;
        panel.add(new JLabel("Segundo número:"), gbc);
        gbc.gridx = 1;
        gbc.weightx = 1;
        panel.add(txtNumeroDos, gbc);

        return panel;
    }

    private JPanel crearPanelOperaciones() {
        JPanel panel = new JPanel(new GridLayout(2, 3, 8, 8));
        panel.setBorder(BorderFactory.createTitledBorder("Operaciones"));

        JButton btnSumar = crearBoton("+  Sumar");
        JButton btnRestar = crearBoton("−  Restar");
        JButton btnMultiplicar = crearBoton("×  Multiplicar");
        JButton btnDividir = crearBoton("÷  Dividir");
        JButton btnLimpiar = crearBoton("Limpiar");
        JButton btnSalir = crearBoton("Salir");

        btnSumar.addActionListener(e -> ejecutarOperacion('+'));
        btnRestar.addActionListener(e -> ejecutarOperacion('-'));
        btnMultiplicar.addActionListener(e -> ejecutarOperacion('*'));
        btnDividir.addActionListener(e -> ejecutarOperacion('/'));
        btnLimpiar.addActionListener(e -> limpiarCampos());
        btnSalir.addActionListener(e -> dispose());

        panel.add(btnSumar);
        panel.add(btnRestar);
        panel.add(btnLimpiar);
        panel.add(btnMultiplicar);
        panel.add(btnDividir);
        panel.add(btnSalir);
        return panel;
    }

    private JButton crearBoton(String texto) {
        JButton boton = new JButton(texto);
        boton.setFont(new Font("SansSerif", Font.BOLD, 13));
        boton.setFocusPainted(false);
        return boton;
    }

    private JPanel crearPanelResultado() {
        JPanel panel = new JPanel(new BorderLayout());
        panel.setBackground(COLOR_FONDO_RESULTADO);
        panel.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(COLOR_PRIMARIO, 1),
                BorderFactory.createEmptyBorder(10, 12, 10, 12)));

        JLabel lblTitulo = new JLabel("Resultado");
        lblTitulo.setFont(new Font("SansSerif", Font.PLAIN, 12));
        lblResultado = new JLabel("—", SwingConstants.RIGHT);
        lblResultado.setFont(new Font("SansSerif", Font.BOLD, 22));
        lblResultado.setForeground(COLOR_PRIMARIO);

        panel.add(lblTitulo, BorderLayout.NORTH);
        panel.add(lblResultado, BorderLayout.CENTER);
        return panel;
    }

    private JPanel crearPanelHistorial() {
        JPanel panel = new JPanel(new BorderLayout());
        panel.setBorder(BorderFactory.createTitledBorder("Historial de operaciones"));
        txtHistorial = new JTextArea(5, 28);
        txtHistorial.setEditable(false);
        txtHistorial.setFont(new Font("Monospaced", Font.PLAIN, 13));
        panel.add(new JScrollPane(txtHistorial), BorderLayout.CENTER);
        return panel;
    }

    /**
     * Lee los dos numeros, ejecuta la operacion indicada y muestra el resultado.
     */
    private void ejecutarOperacion(char operador) {
        Double numeroUno = leerNumero(txtNumeroUno, "primer número");
        if (numeroUno == null) {
            return;
        }
        Double numeroDos = leerNumero(txtNumeroDos, "segundo número");
        if (numeroDos == null) {
            return;
        }

        try {
            double resultado;
            String simbolo;
            switch (operador) {
                case '+':
                    resultado = calculadora.sumar(numeroUno, numeroDos);
                    simbolo = "+";
                    break;
                case '-':
                    resultado = calculadora.restar(numeroUno, numeroDos);
                    simbolo = "−";
                    break;
                case '*':
                    resultado = calculadora.multiplicar(numeroUno, numeroDos);
                    simbolo = "×";
                    break;
                default:
                    resultado = calculadora.dividir(numeroUno, numeroDos);
                    simbolo = "÷";
                    break;
            }
            String operacion = formatoNumero.format(numeroUno) + " " + simbolo + " "
                    + formatoNumero.format(numeroDos) + " = " + formatoNumero.format(resultado);
            lblResultado.setText(operacion);
            txtHistorial.append(operacion + "\n");
        } catch (ArithmeticException ex) {
            lblResultado.setText("Error");
            JOptionPane.showMessageDialog(this, ex.getMessage(),
                    "Operación no válida", JOptionPane.ERROR_MESSAGE);
            txtNumeroDos.requestFocus();
            txtNumeroDos.selectAll();
        }
    }

    /**
     * Valida y convierte el texto de un campo a numero.
     * Devuelve null y muestra un aviso si el dato esta vacio o no es numerico.
     */
    private Double leerNumero(JTextField campo, String nombreCampo) {
        String texto = campo.getText().trim().replace(',', '.');
        if (texto.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Captura el " + nombreCampo + ".",
                    "Dato requerido", JOptionPane.WARNING_MESSAGE);
            campo.requestFocus();
            return null;
        }
        try {
            return Double.parseDouble(texto);
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(this,
                    "El " + nombreCampo + " debe ser un valor numérico.",
                    "Dato no válido", JOptionPane.ERROR_MESSAGE);
            campo.requestFocus();
            campo.selectAll();
            return null;
        }
    }

    private void limpiarCampos() {
        txtNumeroUno.setText("");
        txtNumeroDos.setText("");
        lblResultado.setText("—");
        txtNumeroUno.requestFocus();
    }
}
