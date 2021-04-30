package controlador;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;
import modelo.Logica;
import modelo.Persona;
import vista.GraficoContagiados;
import vista.GraficoMuertes;

public class Controlador implements ActionListener {

    Logica l;
    Timer myTimer = new Timer();
    ArrayList<Persona> persons;

    TimerTask task = new TimerTask() {
        @Override
        public void run() {
            l.llamarTodo(persons);
            persons = l.getLista();
        }
    };

    @Override
    public void actionPerformed(ActionEvent ae) {
        String action = ae.getActionCommand();
        if (action.equals("b")) {
            System.exit(0);
        }
        if (action.equals("g1")) {
            GraficoMuertes gm = new GraficoMuertes(l.muertes);
            gm.setVisible(true);
            gm.setAlwaysOnTop(true);
        }
        if (action.equals("g2")) {
            GraficoContagiados gc = new GraficoContagiados(persons, "Grafico enfermos vs sanos");
            gc.setVisible(true);
            gc.setAlwaysOnTop(true);
            
            
        }
        if (action.equals("algo")) {
            this.persons = this.l.inicializa();
            myTimer.schedule(task, 3000, 2000);
        }

    }

    public void setLogica(Logica l) {
        this.l = l;
    }
}
