package ventana;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import javax.imageio.stream.ImageInputStream;
import javax.swing.JPanel;

public class Panel extends JPanel {
    public BufferedImage image;
    public BufferedImage imagenModificada= new BufferedImage(200,200,BufferedImage.TYPE_INT_RGB);;

    private Image original() throws FileNotFoundException, IOException {
      InputStream Input = new FileInputStream("src/pelusis.png");
      ImageInputStream ImageInput = ImageIO.createImageInputStream(Input);
      BufferedImage ImagenL = ImageIO.read(ImageInput);
      Graphics g = ImagenL.getGraphics();
      g.drawImage(ImagenL, ImagenL.getWidth(),ImagenL.getWidth(), null);
      return ImagenL;
   }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        //doDrawing(g);
        Image image;
        try {
            image = original();
            g.drawImage(image, 100,100,this);
            g.drawImage(imagenModificada, 450, 100, this);
        } catch (IOException ex) {
            Logger.getLogger(Panel.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }

}
