package controlador;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import modelo.Logica;
import vista.Ventana;

public class EventosRaton extends MouseAdapter {
    Logica l;



    public void setLogica(Logica l) {
        this.l = l;
    }

    @Override
    public void mouseClicked(MouseEvent me) {
        l.v.elElegido.setText(l.dameElDato(me.getX(), me.getX()));

    }

}
