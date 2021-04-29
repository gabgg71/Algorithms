package vista;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import javax.swing.AbstractAction;
import javax.swing.Action;
import javax.swing.ImageIcon;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JToolBar;
import modelo.Persona;

public class Panel extends JPanel {
    public ArrayList<Persona> person = new ArrayList<Persona>();

   
    private void doDrawing(Graphics g) {
        Graphics2D g2d = (Graphics2D) g;
        for (Persona person1 : person) {
            g2d.drawOval(person1.getX(), person1.getY(), 4, 4);
            if (person1.getEstado() == 0) {
                g2d.setColor(new Color(120, 120, 250));
                g2d.fillOval(person1.getX(), person1.getY(), 4, 4);
            } else {
                g2d.setColor(new Color(250, 80, 80));
                g2d.fillOval(person1.getX(), person1.getY(), 4, 4);
            }
        }
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        doDrawing(g);
    }
}
