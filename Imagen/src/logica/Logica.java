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
    ArrayList<Integer> a=new ArrayList() , b=new ArrayList(), l=new ArrayList();
    BufferedImage Imagen;
    BufferedImage ImagenAModificar;
    public Cromosoma real= new Cromosoma(new ArrayList(), new ArrayList(), new ArrayList());
    public ArrayList<Cromosoma> generados = new ArrayList();
    public ArrayList<Cromosoma> dis = new ArrayList();

    public void setVentana(Ventana v) {
        this.v = v;
    }

    public Cromosoma imagen() throws FileNotFoundException, IOException {
        try {
            InputStream Input = new FileInputStream("src/pelusis.png");
            ImageInputStream ImageInput = ImageIO.createImageInputStream(Input);
            BufferedImage ImagenL = ImageIO.read(ImageInput);
            this.Imagen = ImagenL;
            this.ImagenAModificar = ImagenL;
            this.v.d.image = ImagenL;

            for (int y = 0; y < ImagenL.getHeight(); y++) {
                for (int x = 0; x < ImagenL.getWidth(); x++) {
                    int srcPixel = ImagenL.getRGB(x, y);
                    Color c = new Color(srcPixel);
                    real.red.add(c.getRed());
                    real.green.add(c.getGreen());
                    real.blue.add(c.getBlue());
                }
            }
            
            System.out.println("Ya guarde todo");
        } catch (IOException e) {
            System.out.println("Hay un error al cargar los pixeles de la imagen");
        }
        
        System.out.println("Tam :"+real.blue.size());
        return this.real;
    }

    public ArrayList<Cromosoma> generarcoloresRandom(Cromosoma real) {
        Random random = new Random();
        generados.add(new Cromosoma(new ArrayList(), new ArrayList(), new ArrayList()));
        int red, green, blue;
        for (int i = 0; i < real.red.size(); i++) {
            red = random.nextInt(256);
            green = random.nextInt(256);
            blue = random.nextInt(256);
            generados.get(0).red.add(red);
            generados.get(0).green.add(green);
            generados.get(0).blue.add(blue);
        }
         //guarda la primera solucion aleatoria
        System.out.println("Tam generados: "+generados.get(0).red.size());
        return this.generados;
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
    public void hallarDistancia() {
        dis.add(new Cromosoma(new ArrayList(), new ArrayList(), new ArrayList()));
        for (int i = 0; i < real.red.size(); i++) {
            dis.get(0).red.add(real.red.get(i) - generados.get(0).red.get(i));
            dis.get(0).green.add(real.green.get(i) - generados.get(0).green.get(i));
            dis.get(0).blue.add(real.blue.get(i) - generados.get(0).blue.get(i));
            if(i < 10){
                System.out.println("real : "+real.red.get(i));
                System.out.println("Generado: "+generados.get(0).red.get(i));
                System.out.println("Distancia: "+dis.get(0).red.get(i));
            }
        }
        
    }

    /*El fitness de un pixel es el promedio de los fitness del mismo para los diferentes
     planos de color, hallado como 1/distancia buscando asi penalizar grandes distancias*/
    public void calcularFitness(int pos) {
        float fr, fg, fb, suma ;
        for (int i = 0; i < real.getBlue().size(); i++) {
            fr = (float) Math.abs(1f/dis.get(pos).red.get(i));
            fg = (float) Math.abs(1f/dis.get(pos).green.get(i));
            fb = (float) Math.abs(1f/dis.get(pos).blue.get(i));
            suma = fr+fg+fb;
            generados.get(pos).f.add(suma/3);   
        }
    }

    /*Nos dice el desempeño en general de toda la poblacion de pixeles*/
    public void fitnessGeneral() {
        float sum = 0f;
        for (int i = 0; i < generados.get(0).f.size(); i++) {
            sum = sum + generados.get(0).f.get(i);
        }
        generados.get(0).setFitness(sum / generados.get(0).f.size()); 
    }

    public ArrayList<Cromosoma> imagenModificada(BufferedImage Imagen) {
        this.ImagenAModificar = Imagen;
        System.out.println("a mod: "+ImagenAModificar.getWidth());
        System.out.println("A mod alto: "+ImagenAModificar.getHeight());
        int contador = 0;
        while (contador < ImagenAModificar.getHeight() * ImagenAModificar.getWidth()) {
            for (int y = 0; y < ImagenAModificar.getHeight(); y++) {
                for (int x = 0; x < ImagenAModificar.getWidth(); x++) {
                    //Retrieving contents of a pixel
                    int pixel = ImagenAModificar.getRGB(x, y);
                    //Creating a Color object from pixel value
                    Color color = new Color(pixel, true);
                    color = new Color(generados.get(0).getRed().get(contador), generados.get(0).getGreen().get(contador), generados.get(0).getBlue().get(contador));
                    //Setting new Color object to the image
                    ImagenAModificar.setRGB(x, y, color.getRGB());
                    contador++;
                }
            }
        }
        generados.get(0).setImagen(ImagenAModificar);
        return generados;
    }

    public void graficar() {
        //this.v.d.image = real.imagen;
        this.v.d.imagenModificada = generados.get(0).imagen;
        this.v.d.repaint();
    }

    public void llamartodo(Cromosoma r, ArrayList<Cromosoma> g) {
        dis.clear();
        generados.get(0).f.clear();
        this.real = r;
        this.generados = g;
        System.out.println(real.blue.size());
        hallarDistancia();
        calcularFitness(0);
        fitnessGeneral();
        graficar();
        seleccionDePixeles(generados.get(0).getRed(), dis.get(0).getRed());
        seleccionDePixeles(generados.get(0).getGreen(), dis.get(0).getGreen());
        seleccionDePixeles(generados.get(0).getBlue(), dis.get(0).getBlue());
        imagenModificada(this.Imagen);
    }

    public Cromosoma real() {
        return real;
    }

    public ArrayList<Cromosoma> generado() {
        return generados;
    }

}
