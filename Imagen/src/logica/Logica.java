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
    //Calcula la distancia en un punto de cada plano de color.
    public void calcularDistancia(int real,int generado){
        if(realRed.contains(real) && generatedRed.contains(generado)){
            disRed.add(realRed.get(real) - generatedRed.get(generado));
            calcularFitness(disRed);
        }
        if(realBlue.contains(real) && generatedBlue.contains(generado)){
            disBlue.add(realBlue.get(real) - generatedBlue.get(generado));
            calcularFitness(disBlue); 
        }        
         if(realGreen.contains(real) && generatedGreen.contains(generado)){
            disGreen.add(realGreen.get(real) - generatedGreen.get(generado));
            calcularFitness(disGreen);
        }       
    }
    //Calcula el fitness y el promedio en todos los pixeles
    public void calcularFitness(ArrayList<Integer> distancia){
        int fitness;
        for(int i = 0; i < distancia.size(); i++){
            fitness = (1/distancia.get(i));
            fitnessGeneral = fitnessGeneral + (1/distancia.get(i));
            
        }
        fitnessGeneral = fitnessGeneral/distancia.size();
       
    }
    
    
    //Calcula la distancia si los puntos se pueden ubicar fuera de su plano de color.
    /*
        public void calcularDistancia(int real,int generado){
        for(int i = 0; i < fitnessPorPixel.size(); i++){
        if(realRed.contains(real) && generatedRed.contains(generado)){
            distancia.add(realRed.get(real) - generatedRed.get(generado));
        }
        if(realRed.contains(real) && generatedBlue.contains(generado)){
            distancia.add(realRed.get(real) - generatedBlue.get(generado));
        }        
         if(realRed.contains(real) && generatedGreen.contains(generado)){
            distancia.add(realRed.get(real) - generatedGreen.get(generado));
        }       
        
        if(realBlue.contains(real) && generatedRed.contains(generado)){
            distancia.add(realBlue.get(real) - generatedRed.get(generado));
        }
        if(realBlue.contains(real) && generatedBlue.contains(generado)){
            distancia.add(realBlue.get(real) - generatedBlue.get(generado));
        }        
         if(realBlue.contains(real) && generatedGreen.contains(generado)){
            distancia.add(realBlue.get(real) - generatedGreen.get(generado));
        }    
         
        if(realGreen.contains(real) && generatedRed.contains(generado)){
            distancia.add(realGreen.get(real) - generatedRed.get(generado));
        }
        if(realGreen.contains(real) && generatedBlue.contains(generado)){
            distancia.add(realGreen.get(real) - generatedBlue.get(generado));
        }        
         if(realGreen.contains(real) && generatedGreen.contains(generado)){
            distancia.add(realGreen.get(real) - generatedGreen.get(generado));
        }
        calcularFitness(distancia);
      }  
    }
    */
}
