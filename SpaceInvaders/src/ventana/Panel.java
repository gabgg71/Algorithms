
package ventana;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import javax.swing.JPanel;

public class Panel extends JPanel{
    public int[][] matriz = new int[0][0];
    public boolean hayDisparo;
    
     private void doDrawing(Graphics g) {
        Graphics2D g2d = (Graphics2D) g;
        
        //Dibujar la matriz
        int b = 0;
        for (int i = 0; i < matriz.length; i++) {
            int a = 0;
            for (int j = 0; j < matriz[0].length; j++) {
                g2d.drawRect(a, b, (550 / matriz.length), (450 / matriz.length));  //Dibujamos el rectangulo
                a += (550 / matriz.length);
            }
            b += (450 / matriz.length);
        }
        

    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        doDrawing(g);
    }
}
