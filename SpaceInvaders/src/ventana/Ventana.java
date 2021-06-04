
package ventana;

import controlador.Controlador;
import java.awt.Color;
import javax.swing.JFrame;
import modelo.Logica;

public class Ventana extends JFrame {
    Logica log;
    Controlador c;
    public Panel d;
    
    public Ventana(Logica log, Controlador c){
        this.log = log;
        this.c = c;
        init();
    }
    
    public void init(){
        setBounds(200,200,1000,650);
        d = new Panel();
        d.setBackground(new Color(0,0,0));
        d.setBounds(0, 0, 1000, 460);
        add(d);
        setTitle("Space Invaders");
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(null);
        
    }
    
}
