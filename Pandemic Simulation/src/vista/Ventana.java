package vista;

import controlador.Controlador;
import controlador.EventosRaton;
import java.awt.Color;
import java.awt.Font;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.ScrollPaneConstants;
import javax.swing.border.LineBorder;
import modelo.Logica;

public class Ventana extends JFrame {

    Logica log;
    public JTextField algo = new JTextField("33");
    public JTextField iteraciones = new JTextField("0");
    public JTextArea notificaMuerte = new JTextArea("Muertes: \n");
    public JTextArea elElegido = new JTextArea("");
    JLabel dia = new JLabel("Dia: ");
    JButton boton = new JButton("Empieza");
    JButton graf1 = new JButton("Tendencias de muerte");
    JButton graf2 = new JButton("Propagación de Covid");
    JButton salida = new JButton("Salida");
    public Controlador c;
    EventosRaton e;
    public Panel d;

    public Ventana(Logica log, Controlador c, EventosRaton e) {
        this.log = log;
        this.c = c;
        this.e = e;
        inicializa();
    }

    private void inicializa() {
        boton.setActionCommand("algo");
        algo.setBounds(1300, 380, 40, 20);
        algo.setBackground(new Color(175, 186, 163));
        add(algo);
        dia.setBounds(720, 500, 60, 40);
        dia.setForeground(Color.white);
        add(dia);
        iteraciones.setBounds(750, 510, 20, 20);
        iteraciones.setBackground(new Color(175, 186, 163));
        add(iteraciones);
        elElegido.setBounds(0,30,400,300);
        elElegido.setForeground(Color.white);
        add(elElegido);
        notificaMuerte.setFont(new Font("Courier", Font.BOLD, 12));
        notificaMuerte.setBorder(new LineBorder(new Color(0, 0, 0), 4, true));
        notificaMuerte.setBackground(new Color(35,31,32));
        notificaMuerte.setForeground(Color.white);
        add(notificaMuerte);
        elElegido.setFont(new Font("Courier", Font.BOLD, 12));
        elElegido.setBorder(new LineBorder(new Color(0, 0, 0), 4, true));
        elElegido.setBackground(new Color(35,31,32));
        JScrollPane sp = new JScrollPane(notificaMuerte);
        sp.setBounds(1070, 30, 500, 300);
        sp.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
        add(sp);
        boton.setBounds(1100, 360, 100, 50);
        boton.setBackground(new Color(129,253,34));
        boton.addActionListener(c);
        add(boton);
        graf1.setBounds(1100, 430, 200, 50);
        graf1.setBackground(new Color(129,253,34));
        graf1.addActionListener(c);
        graf1.setActionCommand("g1");
        add(graf1);
        graf2.setBounds(1100, 490, 200, 50);
        graf2.setBackground(new Color(129,253,34));
        graf2.addActionListener(c);
        graf2.setActionCommand("g2");
        add(graf2);
        d = new Panel();
        d.setBackground(new Color(35,31,32));
        d.setBounds(435, 0, 600, 500);
        d.addMouseListener(e);
        add(d);
        setSize(1600, 600);
        setTitle("Simulacion covid");
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(null);
        salida.setBounds(1400, 500, 85, 25);
        salida.setForeground(Color.RED);
        salida.setBackground(new Color(129,253,34));
        salida.addActionListener(c);
        salida.setActionCommand("b");
        add(salida);
    }
}
