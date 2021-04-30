
package controlador;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Timer;
import java.util.TimerTask;
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
        
    }
    
}
