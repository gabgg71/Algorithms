
package controlador;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.Timer;
import java.util.TimerTask;
import java.util.logging.Level;
import java.util.logging.Logger;
import logica.Logica;

public class Controlador implements ActionListener{
  Logica l;
    Timer myTimer = new Timer();
    
    TimerTask task = new TimerTask() {
        @Override
        public void run() {
            
        }
    };
    
    public void setLogica(Logica l) {
        this.l = l;
    }
    
    @Override
    public void actionPerformed(ActionEvent ae) {
        try {
            l.imagen();
        } catch (IOException ex) {
            Logger.getLogger(Controlador.class.getName()).log(Level.SEVERE, null, ex);
            System.out.println("Hay un error");
        }
        l.generarcoloresRandom(); 
    }
    
}
