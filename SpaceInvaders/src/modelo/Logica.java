
package modelo;

import java.util.ArrayList;
import ventana.Ventana;

public class Logica {
    public Ventana v;
    int[][] matricita;
    public ArrayList<Cromosoma> soluciones = new ArrayList(); //Aqui se guardan los cromosomas de cada generacion
    public ArrayList<Alien> aliens = new ArrayList(); //objetos de tipo alien que se pondran en el juego
    
    public void setVentana(Ventana v) {
        this.v = v;
    }
    
    public void inicializaM(int filas, int columnas) {
        this.matricita = new int[filas][columnas];
        for (int i = 0; i < filas; i++) {
            for (int j = 0; j < columnas; j++) {
                matricita[i][j] = 0;
                
            }
        }
    }
    
    public void llamarTodo() {
        inicializaM(20, 40);
        v.d.matriz = this.matricita;
        v.d.repaint();
    }

}
