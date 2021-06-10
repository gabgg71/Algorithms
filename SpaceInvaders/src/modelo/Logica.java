
package modelo;

import java.util.ArrayList;
import java.util.Collections;
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

    public int[][] inicializaM(int filas, int columnas) {
        this.matricita = new int[filas][columnas];
        for (int i = 0; i < filas; i++) {
            for (int j = 0; j < columnas; j++) {
                matricita[i][j] = 0;

            }
        }
        return matricita;
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
                        aliens.get(w).setJ(aliens.get(w).getJ() + 1);
                    } else {
                        if (aliens.get(w).getI() + 1 < matricita.length - 5) {
                            aliens.get(w).setI(aliens.get(w).getI() + 1);
                        }
                        aliens.get(w).setSentido(1);
                    }

                }
                if (aliens.get(w).getSentido() == 1) {
                    if (aliens.get(w).getJ() - 1 >= 0) {
                        aliens.get(w).setJ(aliens.get(w).getJ() - 1);
                    } else {
                        if (aliens.get(w).getI() + 1 < matricita.length - 5) {
                            aliens.get(w).setI(aliens.get(w).getI() + 1);
                        }
                        aliens.get(w).setSentido(0);
                        aliens.get(w).setJ(aliens.get(w).getJ() + 1);
                    }
                }
            }

            String paso = aProbar.secuencia.substring(j, j + 2); //una accion de la secuencia
            String restoDeSecuenia = aProbar.secuencia.substring(0, j); //por si se muere
            if (paso.equals("00")) {
                for (int w = 0; w < aliens.size(); w++) {
                    if (aliens.get(w).dispara == true && columna == aliens.get(w).j) { //si me disparan y estoy quieto                         
                        aProbar.setSecuencia(restoDeSecuenia + "22"); //muero
                        System.out.println("Me mataron");
                        break;
                    }
                }
            } else if (paso.equals("01")) {  //voy a la derecha
                if (columna + 1 < matricita[0].length) {
                    columna++;
                }
                for (int w = 0; w < aliens.size(); w++) {
                    if (aliens.get(w).dispara == true && columna == aliens.get(w).j) {
                        aProbar.setSecuencia(restoDeSecuenia + "22"); //si al moverme me matan
                        System.out.println("Me mataron");
                        break;
                    }
                }
            } else if (paso.equals("10")) {
                if (columna - 1 >= 0) {
                    columna--;
                }
                for (int w = 0; w < aliens.size(); w++) {
                    if (aliens.get(w).dispara == true && columna == aliens.get(w).j) {
                        aProbar.setSecuencia(restoDeSecuenia + "22");
                        System.out.println("Me mataron");
                        break;
                    }
                }
            } else if (paso.equals("11")) {
                for (int w = 0; w < aliens.size(); w++) {
                    if (aliens.get(w).dispara == true && columna == aliens.get(w).j) { //si disparo y me disparan al tiempo
                        System.out.println("Casi mato choque de disparos");
                    } else if (aliens.get(w).dispara == false && columna == aliens.get(w).j) { //si le doy
                        aProbar.setFitness(aProbar.getFitness() + 1);
                        v.d.hayDisparo = false;
                        aliens.remove(w);
                        System.out.println("Mate a alguien");
                    }
                }
                }
                for (int y = 0; y < aliens.size(); y++) {  //validacion para el sentido en el que se mueven los elementos
                    aliens.get(y).setDispara(false);
                }
            }
        }
          public ArrayList<Cromosoma> generaSecuencias(int n){ //Para los primeras n secuencias
        String seq = "";
        int pos1;
        //Generamos 100 acciones por turno (generacion)
        for (int j = 0; j < n; j++) {
            for (int i = 0; i < 200; i++) {
                pos1 = (int) (Math.random() * (2));
                seq = seq + pos1;
            }
            soluciones.add(new Cromosoma(seq));
            seq = "";
        }
        return soluciones;
    }

    public void ruleta(int desde, int hasta) { //Para cruzar las secuencias existentes
        System.out.println("Entre a ruleta");
        for (int i = desde; i < hasta; i++) {
            int parejaAleatoria = (int) (Math.random() * (soluciones.size()) - 1);
            String hijo;

            String agregar = soluciones.get(i).getSecuencia().substring(0, soluciones.get(i).secuencia.length() - 2);
            while (soluciones.get(i).secuencia.length() < 200) {
                int falta = 200 - soluciones.get(i).secuencia.length() + 2;
                if (soluciones.get(i).secuencia.length() > falta) {
                    agregar = agregar + soluciones.get(i).secuencia.substring(0, falta);
                    if(agregar.substring(agregar.length()-2, agregar.length()).equals("22")){
                        agregar = agregar.substring(0, agregar.length()-2);
                    }
                } else {
                    if (((agregar.length() + soluciones.get(i).secuencia.length()-2) >= 200)) {
                        agregar = agregar + soluciones.get(i).secuencia.substring(0, falta);
                       /* if(agregar.substring(agregar.length()-2, agregar.length()).equals("22")){
                        agregar = agregar.substring(0, agregar.length()-2);
                    }*/
                    } else {
                        if(soluciones.get(i).secuencia.length() > 2){
                            agregar = agregar + soluciones.get(i).secuencia.substring(0, soluciones.get(i).secuencia.length()-2);
                        }else{
                            agregar = agregar + "01";
                        }  
                    }
                }
                soluciones.get(i).setSecuencia(agregar);
            }
            if (soluciones.get(i).getSecuencia().length() > 200) {
                int sobra = soluciones.get(i).getSecuencia().length() - 200;
                soluciones.get(i).setSecuencia(soluciones.get(i).secuencia.substring(0, soluciones.get(i).getSecuencia().length() - sobra));
            }
            agregar = soluciones.get(parejaAleatoria).getSecuencia().substring(0, soluciones.get(parejaAleatoria).secuencia.length() - 2);
            while (soluciones.get(parejaAleatoria).secuencia.length() < 200) {
                int falta = 200 - soluciones.get(parejaAleatoria).secuencia.length() + 2;
                if (soluciones.get(parejaAleatoria).secuencia.length() > falta) {
                    agregar = agregar + soluciones.get(parejaAleatoria).secuencia.substring(0, falta);
                    if(agregar.substring(agregar.length()-2, agregar.length()).equals("22")){
                        agregar = agregar.substring(0, agregar.length()-2);
                    }
                } else {
                    if (((agregar.length() + soluciones.get(parejaAleatoria).secuencia.length()-2) >= 200)) {
                        agregar = agregar + soluciones.get(parejaAleatoria).secuencia.substring(0, falta);
                        if(agregar.substring(agregar.length()-2, agregar.length()).equals("22")){
                        agregar = agregar.substring(0, agregar.length()-2);
                    }
                    } else {
                        if(soluciones.get(i).secuencia.length() > 2){
                            agregar = agregar + soluciones.get(i).secuencia.substring(0, soluciones.get(i).secuencia.length()-2);
                        }else{
                            agregar = agregar + "01";
                        } 
                    }
                }

                soluciones.get(parejaAleatoria).setSecuencia(agregar);

            }
            if (soluciones.get(parejaAleatoria).getSecuencia().length() > 200) {
                int sobra = soluciones.get(parejaAleatoria).getSecuencia().length() - 200;
                soluciones.get(parejaAleatoria).setSecuencia(soluciones.get(parejaAleatoria).secuencia.substring(0, soluciones.get(parejaAleatoria).getSecuencia().length() - sobra));
            }
            System.out.println("Hice validaciones");
            hijo = soluciones.get(i).secuencia.substring(0, soluciones.get(i).secuencia.length() / 2) + soluciones.get(parejaAleatoria).secuencia.substring((soluciones.get(parejaAleatoria).secuencia.length() / 2)-2, soluciones.get(parejaAleatoria).secuencia.length()-2);
            soluciones.add(new Cromosoma(hijo));
            System.out.println("valor de i: " + i + "Agrege hijo");
        }
    }

    public ArrayList<Cromosoma> getSoluciones() {
        return soluciones;
    }
        
    public void llamarTodo() {
        matricita = inicializaM(20, 40);
        this.copia = (ArrayList)losAliens(20).clone();
        initArreglos(copia.size()); 
        for(int i = 0; i < copia.size(); i++){
            is[i] = copia.get(i).i;
            js[i] = copia.get(i).j;
            tipos[i] = copia.get(i).tipo;
        }       
        this.soluciones = generaSecuencias(10);

        //jugando las 10 aleatorias
        for (int i = 0; i < soluciones.size(); i++) {
            jugandoSin(soluciones.get(i));
        }

        for (int i = 0; i < soluciones.size(); i++) {
            System.out.println("Secuencia: " + soluciones.get(i).secuencia);
            System.out.println("Acciones que realizo: " + soluciones.get(i).secuencia.length() / 2);
            System.out.println("Fitness de la secuencia: " + (i + 1) + ": " + soluciones.get(i).fitness);
        }

         System.out.println(" despues de ruleta----------------------------------------------------------");
         int desde = soluciones.size()-10;
         int hasta = soluciones.size();
         for(int k = 0; k<3;k++){
         ruleta(desde, hasta);
         for (int i = desde; i < hasta; i++) {
         jugandoSin(soluciones.get(i));
         System.out.println("Secuencia: " + soluciones.get(i).secuencia);
         System.out.println("Longitud secuencia: "+soluciones.get(i).secuencia.length());
         System.out.println("Acciones que realizo: " + soluciones.get(i).secuencia.length() / 2);
         }  
         }
        
         System.out.println("Iterando en soluciones: ");
         for(int i =0; i<soluciones.size();i++){
             System.out.println("Secuencia: "+soluciones.get(i).secuencia);
             System.out.println("Fitness: "+soluciones.get(i).fitness);
         }
         
         

         Collections.sort(soluciones, Cromosoma.porFitness);
         System.out.println("TOTAL SOLUCIONES: " + soluciones.size());
         System.out.println("IMPRIMIENDO LA MEJOR SOLUCION: ");
         System.out.println("La secuencia antes de jugar es: " + soluciones.get(0).secuencia);
         System.out.println("Su fitnees es : "+soluciones.get(0).fitness);
         jugandoSin(soluciones.get(0));
         System.out.println("Secuencia: " + soluciones.get(0).secuencia);
         System.out.println("Longitud secuencia: "+soluciones.get(0).secuencia.length());
         System.out.println("Acciones que realizo: " + soluciones.get(0).secuencia.length() / 2);
         System.out.println("Fitness de la mejor secuencia: " + soluciones.get(0).fitness);
              
    }

    public static void esperar() {
        try {
            Thread.sleep(30);
        } catch (Exception e) {
            System.out.println(e);
        }
    }
}


