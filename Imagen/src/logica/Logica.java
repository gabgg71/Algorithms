
package logica;

import java.awt.Color;
import java.awt.image.BufferedImage;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Random;
import javax.imageio.ImageIO;
import javax.imageio.stream.ImageInputStream;
import ventana.Ventana;

public class Logica {
    public Ventana v;

    BufferedImage Imagen;
    public ArrayList<Integer> realRed = new ArrayList(),realGreen= new ArrayList(), realBlue= new ArrayList();
    public ArrayList<Integer> generatedRed= new ArrayList(),generatedGreen= new ArrayList(),generatedBlue = new ArrayList();
    public ArrayList<Integer> disRed= new ArrayList(), disGreen= new ArrayList(), disBlue = new ArrayList();
    public ArrayList<Integer> fitnessPorPixel; 
    int fitnessGeneral;

    public void setVentana(Ventana v) {
        this.v = v;
    }

    public void imagen() throws FileNotFoundException, IOException {
        try {
            InputStream Input = new FileInputStream("src/pelusis.png");
            ImageInputStream ImageInput = ImageIO.createImageInputStream(Input);
            BufferedImage ImagenL = ImageIO.read(ImageInput);
            this.Imagen = ImagenL;
            for (int y = 0; y < ImagenL.getHeight(); y++) {
                for (int x = 0; x < ImagenL.getWidth(); x++) {
                    int srcPixel = ImagenL.getRGB(x, y);
                    Color c = new Color(srcPixel);
                    realRed.add(c.getRed());
                    realGreen.add(c.getGreen());
                    realBlue.add(c.getBlue());
                }
            }
            System.out.println("Ya guarde todo");
        } catch (IOException e) {
            System.out.println("Hay un error al cargar los pixeles de la imagen");
        }
    }

    public void generarcoloresRandom() {
        Random random = new Random();
        int red, green, blue;
        for (int i = 0; i < Imagen.getHeight() * Imagen.getWidth(); i++) {
            red = random.nextInt(256);
            green = random.nextInt(256);
            blue = random.nextInt(256);
            generatedRed.add(red);
            generatedGreen.add(green);
            generatedBlue.add(blue);
        }
    }
}
