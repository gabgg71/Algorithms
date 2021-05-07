
package ventana;
import controlador.Controlador;
import java.awt.Color;
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
    public JLabel coincidencia = new JLabel("Hay x pixeles aceptables de los y posibles");
    JLabel fit = new JLabel("Fitness cromosoma: ");
    public JTextField resulFitness = new JTextField("0");
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
        d.setBounds(40, 20, 1180, 600);
        add(d);
        generacion.setBounds(50, 640, 100, 40);
        generacion.setForeground(Color.red);
        add(generacion);
        coincidencia.setBounds(50, 700, 320, 40);
        coincidencia.setForeground(Color.red);
        add(coincidencia);
        fit.setBounds(50, 665, 140, 40);
        fit.setForeground(Color.red);
        add(fit);
        anotacion .setBounds(500, 700,700, 40);
        anotacion .setForeground(Color.red);
        add(anotacion);
        iteraciones.setBounds(190, 650, 120, 20);
        iteraciones.setBackground(new Color(175, 186, 163));
        add(iteraciones);
        resulFitness.setBounds(190, 675, 120, 20);
        resulFitness.setBackground(new Color(175, 186, 163));
        add(resulFitness);
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
