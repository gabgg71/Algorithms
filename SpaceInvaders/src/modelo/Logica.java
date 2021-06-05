
package modelo;

import java.util.ArrayList;
import java.util.Timer;
import ventana.Ventana;

public class Logica {
    
    public Ventana v;
    int[][] matricita;
    public ArrayList<Cromosoma> soluciones = new ArrayList(); //Aqui se guardan los cromosomas de cada generacion
    public ArrayList<Alien> aliens = new ArrayList(); //objetos de tipo alien que se pondran en el juego
    public ArrayList<Alien> copia = new ArrayList();
    int patronDisparo, pp =0;
    public Timer myTimer = new Timer();
    int [] is;
    int [] js; 
    int [] tipos ; 
    
    public void setVentana(Ventana v) {
        this.v = v;
    }
    
       public void initArreglos(int tamAliens){
         is = new int [tamAliens];
         js = new int [tamAliens]; 
         tipos = new int [tamAliens];
    }

    public void inicializaM(int filas, int columnas) {
        this.matricita = new int[filas][columnas];
        for (int i = 0; i < filas; i++) {
            for (int j = 0; j < columnas; j++) {
                matricita[i][j] = 0;
                
            }
        }
    }
    
        public ArrayList<Alien> losAliens(int n) {
        for (int k = 0; k < n; k++) {
            int i = (int) (Math.random() * (matricita.length)) + matricita.length / 5;
            int j = (int) (Math.random() * (matricita[0].length));
            int tipo = (int) (Math.random() * (3));
            for (int l = 0; l < aliens.size(); l++) {
                while (aliens.get(l).i == i && aliens.get(l).j == j || i > 10) { // para que no queden dos aliens en la misma pos
                    i = (int) (Math.random() * (matricita.length));
                    j = (int) (Math.random() * (matricita[0].length));
                }
            }
            aliens.add(new Alien(i, j));
            aliens.get(k).setDispara(true);
            aliens.get(k).setTipo(tipo);
        }
        return aliens;
    }
        
        public void jugandoSin(Cromosoma aProbar) {
        //iteramos en los cromosomas 
        aliens.clear();
        for(int i =0; i<copia.size(); i++){
            aliens.add(new Alien(is[i], js[i]));
            aliens.get(i).setTipo(tipos[i]);
            aliens.get(i).setDispara(false);
            aliens.get(i).setSentido(0);
        }
        int fila = matricita.length - 1;
        int columna = (matricita[0].length / 2);
        aProbar.setFitness(0);
        // 01 derecha 10 izquierda 00 centro 11 dispara

        for (int j = 0; j < aProbar.secuencia.length(); j = j + 2) {
            for (int w = 0; w < aliens.size(); w++) { //movimiento y disparos de los aliens
                patronDisparo = 3;
                if (w % patronDisparo == 0) {
                    aliens.get(w).setDispara(true);
                }
                if (aliens.get(w).getSentido() == 0) {
                    if (aliens.get(w).getJ() + 1 < matricita[0].length) {
                        
                    }
                }
            }
        }
        }
        
    public void llamarTodo() {
        inicializaM(20, 40);
        v.d.matriz = this.matricita;
        v.d.repaint();
    }

}
