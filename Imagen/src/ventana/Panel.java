
package ventana;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import javax.swing.JPanel;

public class Panel extends JPanel{
    private void doDrawing(Graphics g) {
        Graphics2D g2d = (Graphics2D) g;

    }
    
    
    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        doDrawing(g);
    }
    
}
