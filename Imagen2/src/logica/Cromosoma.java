/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package logica;

import java.awt.image.BufferedImage;
import java.util.ArrayList;
public class Cromosoma {
    public ArrayList<Integer> red;
    public ArrayList<Integer> green;
    public ArrayList<Integer> blue;
    public ArrayList<Float> f = new ArrayList();
    public float fitness; //fitness de cada solucion
    BufferedImage imagen;

    public Cromosoma(ArrayList<Integer> realRed, ArrayList<Integer> realGreen, ArrayList<Integer> realBlue) {
        this.blue = realBlue;
        this.green = realGreen;
        this.red = realRed;
    }

    public Cromosoma(BufferedImage imagen) {
        this.imagen = imagen;
    }

    public Cromosoma(ArrayList<Integer> realRed, ArrayList<Integer> realGreen, ArrayList<Integer> realBlue, int fitness, BufferedImage imagen) {
        this.blue = realBlue;
        this.green = realGreen;
        this.red = realRed;
        this.fitness = fitness;
        this.imagen = imagen;
    }

    public ArrayList<Integer> getRed() {
        return red;
    }

    public void setRed(ArrayList<Integer> red) {
        this.red = red;
    }

    public ArrayList<Integer> getGreen() {
        return green;
    }

    public void setGreen(ArrayList<Integer> green) {
        this.green = green;
    }

    public ArrayList<Integer> getBlue() {
        return blue;
    }

    public void setBlue(ArrayList<Integer> blue) {
        this.blue = blue;
    }

    public ArrayList<Float> getF() {
        return f;
    }

    public void setF(ArrayList<Float> f) {
        this.f = f;
    }

    public float getFitness() {
        return fitness;
    }

    public void setFitness(float fitness) {
        this.fitness = fitness;
    }


    public BufferedImage getImagen() {
        return imagen;
    }

    public void setImagen(BufferedImage imagen) {
        this.imagen = imagen;
    }
}
