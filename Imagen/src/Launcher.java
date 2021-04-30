
import controlador.Controlador;
import java.awt.Color;
import logica.Logica;
import ventana.Ventana;


public class Launcher {
    public static void main(String[] args) {
        Logica log = new Logica();
        Controlador c = new Controlador();
        c.setLogica(log);
        Ventana ex = new Ventana(log, c);
        ex.getContentPane().setBackground(new Color(58,120,200));
        log.setVentana(ex);
        ex.setVisible(true);
        ex.setAlwaysOnTop(true);
    }
}
