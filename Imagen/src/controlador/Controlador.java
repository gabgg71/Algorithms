package controlador;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;
import java.util.logging.Level;
import java.util.logging.Logger;
import logica.Cromosoma;
import logica.Logica;

public class Controlador implements ActionListener{
Logica l;
Cromosoma real;
ArrayList<Cromosoma> generados; 



    Timer myTimer = new Timer();
    
    TimerTask task = new TimerTask() {
        @Override
        public void run() {
            l.llamartodo(real, generados);
        }
    };
    
    public void setLogica(Logica l) {
        this.l = l;
    }
    
    @Override
    public void actionPerformed(ActionEvent ae) {
        try {
            this.real = l.imagen();
        } catch (IOException ex) {
            Logger.getLogger(Controlador.class.getName()).log(Level.SEVERE, null, ex);
            System.out.println("Hay un error");
        }
         this.generados =l.generarcoloresRandom(this.real); 
         
        
        myTimer.schedule(task,10000,1000);
    }
  
}

   
    

