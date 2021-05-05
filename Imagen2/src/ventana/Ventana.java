
package ventana;
import controlador.Controlador;
import java.awt.Color;
import java.io.File;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;
import logica.Logica;
public class Ventana extends JFrame{
    Logica log;
    Controlador c;
    public Panel d;
    JButton boton = new JButton("Empieza");
    JButton boton2 = new JButton("Seleccionar foto");
    JButton salida = new JButton("Salir");
    public JTextField iteraciones = new JTextField("0");
    JLabel generacion = new JLabel("Generacion: ");
    public JFileChooser fc = new JFileChooser();
    JLabel anotacion = new JLabel("Si presiona el boton seleccionar, despues no es necesario"
            + " presionar el de empezar, lo hace automaticamente.");
    
    public Ventana(Logica log, Controlador c){
        this.log = log;
        this.c = c;
        init();
    }
    
    public void init(){
        setBounds(200,200,1300,900);
        d = new Panel();
        d.setBackground(new Color(150,150,150));
        d.setBounds(40, 0, 1180, 700);
        add(d);
        generacion.setBounds(500, 100, 60, 40);
        generacion.setForeground(Color.red);
        d.add(generacion);
        anotacion .setBounds(500, 700,600, 40);
        anotacion .setForeground(Color.red);
        add(anotacion);
        iteraciones.setBounds(800, 100, 40, 20);
        iteraciones.setBackground(new Color(175, 186, 163));
        d.add(iteraciones);
        setTitle("Regeneracion de imagenes");
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(null);
        boton.setBounds(600, 800, 150, 50);
        boton.addActionListener(c);
        boton.setActionCommand("b1");
        add(boton);
        boton2.setBounds(300, 800, 300, 50);
        boton2.addActionListener(c);
        boton2.setActionCommand("b2");
        add(boton2);
        salida.setBounds(750, 800, 150, 50);
        salida.addActionListener(c);
        salida.setActionCommand("b3");
        add(salida);
    }
}
