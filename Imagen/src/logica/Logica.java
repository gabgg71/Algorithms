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
    public ArrayList<Integer> realRed = new ArrayList(), realGreen = new ArrayList(), realBlue = new ArrayList();
    public ArrayList<Integer> generatedRed = new ArrayList(), generatedGreen = new ArrayList(), generatedBlue = new ArrayList();
    public ArrayList<Integer> disRed = new ArrayList(), disGreen = new ArrayList(), disBlue = new ArrayList();
    public ArrayList <Float> fitnessPorPixel = new ArrayList <> ();
    float fitnessGeneral;

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

    /*Metodo que cambia el valor de los pixeles de la imagen generada teniendo en 
     cuenta el fitness, recibe un arraylist de pixeles de un plano de color y las
     distancias correspondientes de dicho plano generado con el real*/
    public void seleccionDePixeles(ArrayList<Integer> generados, ArrayList<Integer> distancia) {
        int agregado;
        for (int i = 0; i < generados.size(); i++) {
            if (distancia.get(i) > 0) {
                agregado = (int) (Math.random() * (distancia.get(i)));
            } else {
                agregado = (int) (Math.random() * (distancia.get(i)) + 0);
            }
            generados.set(i, generados.get(i) + agregado);
        }
    }
    
    /*Este metodo halla las distancias entre los pixeles de un plano real con el generado
    recibe el plano real de color ejm: realRed, el generado ejm: generatedRed y el 
    arraylist en el que guardara las distancias ejm: disRed*/
    public ArrayList<Integer> hallarDistancia(ArrayList<Integer> real,ArrayList<Integer> generado, ArrayList<Integer> dist){
        for(int i =0; i < real.size();i++){
            dist.add(real.get(i)-generado.get(i));
        }
        return dist;
    }
    
    /*El fitness de un pixel es el promedio de los fitness del mismo para los diferentes
    planos de color, hallado como 1/distancia buscando asi penalizar grandes distancias*/
    public void calcularFitness(){
        for(int i =0 ; i< realRed.size();i++){
            fitnessPorPixel.add((((1/disRed.get(i))+(1/disBlue.get(i))+(1/disGreen.get(i)))/3f)); 
        }
    }
    
    /*Nos dice el desempeño en general de toda la poblacion de pixeles*/
    public void fitnessGeneral(){
        float suma =0f;
        for(int i =0 ; i< fitnessPorPixel.size();i++){
             suma+= fitnessPorPixel.get(i);
        }
        this.fitnessGeneral = suma / fitnessPorPixel.size();
    }

}
