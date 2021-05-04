package ventana;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.JPanel;

public class Panel extends JPanel {

    public String url;
    public Image image;
    public Image imagenModificada;
   

    private void doDrawing(Graphics g) {
        Graphics2D g2d = (Graphics2D) g;

        File imagen = new File(url);

        try {
            image = ImageIO.read(imagen);
        } catch (IOException ex) {

            System.out.println("Fallo imagen");

        }

        g2d.drawImage(image, 100, 100, null);

        g2d.drawImage(imagenModificada, 400, 100, null);

    }

    @Override
    public void paintComponent(Graphics g) {
        g.drawImage(image, 0, 0, null);
    }

}