/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controlador;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JFileChooser;
import logica.Cromosoma;
import logica.Logica;

public class Controlador implements ActionListener {
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
        String action = ae.getActionCommand();
        if (action.equals("b1")) {
            try {
                this.real = l.imagen("");
            } catch (IOException ex) {
                Logger.getLogger(Controlador.class.getName()).log(Level.SEVERE, null, ex);
                System.out.println("Hay un error");
            }
            this.generados = l.generarcoloresRandom(this.real);

            myTimer.schedule(task, 2000, 500);
        }
        if (action.equals("b2")) {
            int respuesta = l.v.fc.showOpenDialog(l.v);
            //Comprobar si se ha pulsado Aceptar
            if (respuesta == JFileChooser.APPROVE_OPTION) {
                //Crear un objeto File con el archivo elegido
                File archivoElegido = l.v.fc.getSelectedFile();
                //Mostrar el nombre del archvivo en un campo de texto
                try {
                    this.real = l.imagen(archivoElegido.getAbsolutePath());
                } catch (IOException ex) {
                    Logger.getLogger(Controlador.class.getName()).log(Level.SEVERE, null, ex);
                    System.out.println("Hay un error");
                }
                this.generados = l.generarcoloresRandom(this.real);

                myTimer.schedule(task, 10000, 1000);
            }
        }
        if(action.equals("b3")){
            System.exit(0);
        }
    }
}
