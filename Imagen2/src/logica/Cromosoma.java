/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package logica;

import java.awt.image.BufferedImage;
import java.util.ArrayList;
public class Cromosoma {
    public ArrayList<String> red;
    public ArrayList<String> green;
    public ArrayList<String> blue;
    public float fitness; //fitness de cada solucion
    BufferedImage imagen;
    int posicion;

    public Cromosoma(ArrayList<String> realRed, ArrayList<String> realGreen, ArrayList<String> realBlue) {
        this.blue = realBlue;
        this.green = realGreen;
        this.red = realRed;
    }

    public Cromosoma(BufferedImage imagen) {
        this.imagen = imagen;
    }

    public Cromosoma(ArrayList<String> realRed, ArrayList<String> realGreen, ArrayList<String> realBlue, int fitness, BufferedImage imagen) {
        this.blue = realBlue;
        this.green = realGreen;
        this.red = realRed;
        this.fitness = fitness;
        this.imagen = imagen;
    }

    public ArrayList<String> getRed() {
        return red;
    }

    public void setRed(ArrayList<String> red) {
        this.red = red;
    }

    public ArrayList<String> getGreen() {
        return green;
    }

    public void setGreen(ArrayList<String> green) {
        this.green = green;
    }

    public ArrayList<String> getBlue() {
        return blue;
    }

    public void setBlue(ArrayList<String> blue) {
        this.blue = blue;
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

    public int getPosicion() {
        return posicion;
    }

    public void setPosicion(int posicion) {
        this.posicion = posicion;
    }
}
