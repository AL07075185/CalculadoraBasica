package mx.tecmilenio.calculadora;

import javax.swing.SwingUtilities;
import javax.swing.UIManager;

/**
 * Punto de entrada de la aplicacion.
 * Aplica el aspecto visual del sistema operativo y abre la ventana de la calculadora.
 */
public class Main {

    public static void main(String[] args) {
        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (Exception e) {
        }
        SwingUtilities.invokeLater(() -> new CalculadoraGUI().setVisible(true));
    }
}
