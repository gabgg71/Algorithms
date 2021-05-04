package controlador;

import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.Timer;
import java.util.TimerTask;
import java.util.logging.Level;
import java.util.logging.Logger;
import logica.Colores;
import logica.Logica;

public class Controlador implements ActionListener{

Logica l;
Image Imagen;
Colores c;



    Timer myTimer = new Timer();
    
    TimerTask task = new TimerTask() {
        @Override
        public void run() {
            l.llamartodo();
            
            
        }
    };
    
    public void setLogica(Logica l) {
        this.l = l;
    }
    
    @Override
    public void actionPerformed(ActionEvent ae) {
        try {
            
            this.Imagen = l.imagen();
        } catch (IOException ex) {
            Logger.getLogger(Controlador.class.getName()).log(Level.SEVERE, null, ex);
            System.out.println("Hay un error");
        }
        l.generarcoloresRandom(); 
        
        myTimer.schedule(task,500,700);
    }
  
}

   
    

