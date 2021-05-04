package ventana;

import controlador.Controlador;
import java.awt.Color;
import javax.swing.JButton;
import javax.swing.JFrame;
import logica.Logica;

public class Ventana extends JFrame{
    Logica log;
    Controlador c;
    public Panel d;
    JButton boton = new JButton("Empieza");

    
    public Ventana(Logica log, Controlador c){
        this.log = log;
        this.c = c;
        init();
    }
    
    public void init(){
        setBounds(200,200,1000,600);
        d = new Panel();
        d.setBackground(new Color(150,150,150));
        d.setBounds(40, 0, 900, 460);
        add(d);
        setTitle("Regeneracion de imagenes");
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(null);
        boton.setBounds(460, 500, 150, 50);
        boton.addActionListener(c);
        add(boton);
    }
}
