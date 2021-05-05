/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
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
    BufferedImage ImagenAModificar;
    public Cromosoma real= new Cromosoma(new ArrayList(), new ArrayList(), new ArrayList());
    public ArrayList<Cromosoma> generados = new ArrayList();
    public ArrayList<Cromosoma> dis = new ArrayList();
    int v1;
    String ur; 

    public void setVentana(Ventana v) {
        this.v = v;
    }

    public Cromosoma imagen(String url) throws FileNotFoundException, IOException {
        try {
            InputStream Input;
            if(url==""){
                ur = "src/pelusis.png";
                Input = new FileInputStream("src/pelusis.png");
            }else{
                ur = url;
                Input = new FileInputStream(url);
            }
            
            ImageInputStream ImageInput = ImageIO.createImageInputStream(Input);
            BufferedImage ImagenL = ImageIO.read(ImageInput);
            this.Imagen = ImagenL;
            this.ImagenAModificar = ImagenL;
            this.v.d.url = url; 
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
        int contador = 0;
        while (contador < ImagenAModificar.getHeight() * ImagenAModificar.getWidth()) {
            for (int y = 0; y < ImagenAModificar.getHeight(); y++) {
                for (int x = 0; x < ImagenAModificar.getWidth(); x++) {
                    int pixel = ImagenAModificar.getRGB(x, y);
                    Color color = new Color(pixel, true);
                    color = new Color(generados.get(0).getRed().get(contador), generados.get(0).getGreen().get(contador), generados.get(0).getBlue().get(contador));
                    ImagenAModificar.setRGB(x, y, color.getRGB());
                    contador++;
                }
            }
        }
        generados.get(0).setImagen(ImagenAModificar);
        return generados;
    }

    public void graficar() {
        //this.v.d.image = Imagen;
        this.v.d.url= ur;
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
        v1 = Integer.parseInt(v.iteraciones.getText().trim()) + 1;
        v.iteraciones.setText("" + v1 + "");
    }

    public Cromosoma real() {
        return real;
    }

    public ArrayList<Cromosoma> generado() {
        return generados;
    }
}
