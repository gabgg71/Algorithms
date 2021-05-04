

import controlador.Controlador;
import controlador.EventosRaton;
import java.awt.Color;
import modelo.Logica;
import vista.Ventana;

public class Launcher {

    public static void main(String[] args) {
        Logica log = new Logica();
        Controlador c = new Controlador();
        EventosRaton e = new EventosRaton();
        c.setLogica(log);
        e.setLogica(log);
        Ventana ex = new Ventana(log, c, e);
        ex.getContentPane().setBackground(new Color(58,62,67));
        log.setVentana(ex);
        ex.setVisible(true);
        ex.setAlwaysOnTop(true);
        

    }
}
