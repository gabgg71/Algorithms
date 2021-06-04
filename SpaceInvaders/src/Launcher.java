
import controlador.Controlador;
import java.awt.Color;
import modelo.Logica;
import ventana.Ventana;


public class Launcher {
    public static void main(String[] args) {
        Logica log = new Logica();
        Controlador c = new Controlador();
        c.setLogica(log);
        Ventana ex = new Ventana(log, c);
        ex.getContentPane().setBackground(new Color(58,62,67));
        log.setVentana(ex);
        log.llamarTodo();
        ex.setVisible(true);
        ex.setAlwaysOnTop(true);
    }
}
