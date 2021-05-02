package logica;

import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;

public class Poblacion {
    private Cromosoma[] cromosomas;

    public Poblacion(int length) {
        this.cromosomas = new Cromosoma[length];
    }
    
    public Poblacion inicializarPoblacion(){
        for(int i = 0; i < cromosomas.length; i++){
            cromosomas[i] = new Cromosoma(AlgoritmoG.Cromosoma_Fitness.length).inicializarCromosoma();
        }
        ordenarCromosomasFitness();
        return this;
    }

    public Cromosoma[] getCromosomas() {
        return cromosomas;
    }
  
      public void ordenarCromosomasFitness() {
      //Este array sort no funciona en java 7, solo en java 8 en adelante
      //Si manda error cambiar el jdk a java 8 o buscar una alternativa
      //para el lambda.
        Arrays.sort(cromosomas, (cromosoma1, cromosoma2) ->{
            int flag = 0;
            if(cromosoma1.getFitness() > cromosoma2.getFitness())flag = -1;
                    else  flag =1;
            return flag;
           });
    }
}