
package logica;
/**
 *
 * @author Daniel Molina
 */
public class AlgoritmoG {
    //Variables usadas en el algoritmo genetico:
    //Se selecciona que tan grande es la poblacion, recomendable poner los pixeles.
    public static final int tamaño_poblacion = 4;
    
    //Se selecciona a que deberia llegar la poblacion, poner el codigo de la imagen.
    public static final int[] Cromosoma_Fitness = {1,1,0,0,1,1,1,1};
    
    //Que cromosomas fuertes se quieren en la poblacion, no poner un valor mayor a la poblacion
    //Aumentar el valor hara que se demore menos en terminar el algoritmo
    public static final int Cromosomas_Elite = 1;
    
    //Cuantos cromosomas se deberian cruzar por el metodo de torneo.
    //Se recomienda poner el mismo valor de la poblacion así todos los valores
    //Se cruzaran.
    public static final int Tournament = 4;
    
    //Que tan frecuente se quiere una mutacion. 1 es el valor maximo el cual es siempre.
    public static final double frecuencia_Mutaciones = 0.25;  
    
    
   
    /*Metodos para calcular el fitness*/
    //Metodo principal: Se utiliza para avanzar la poblacion hacia el fitness
    //Siempre se debe ejecutar.
     public Poblacion evolucion(Poblacion poblacion){
        return mutaciones(cruzarPoblacion(poblacion));
    }
    //Primero la funcion selecciona los cromosomas por metodo de torneo
    //luego se cruzan en cruzar cromosomas y despues se añaden en la poblacion.
    private Poblacion cruzarPoblacion(Poblacion poblacion){
        Poblacion cruces = new Poblacion(poblacion.getCromosomas().length);
        for(int i = 0; i < Cromosomas_Elite; i++){
            cruces.getCromosomas()[i] = poblacion.getCromosomas()[i];
        }
        for(int i = Cromosomas_Elite; i < poblacion.getCromosomas().length; i++){
            Cromosoma cromosoma1 = seleccionarCromosomasTournament(poblacion).getCromosomas()[0];
            Cromosoma cromosoma2 = seleccionarCromosomasTournament(poblacion).getCromosomas()[0];
            cruces.getCromosomas()[i] = crucescromosomas(cromosoma1,cromosoma2);
        }
        return cruces;
    }
    //Un metodo utilizado en algoritmo genetico, las mutaciones basicamente
    //cambian un valor por uno aleatorio, de esta forma se puede alcanzar
    //un rango que originalmente no se podia con la poblacion inicial.
    private Poblacion mutaciones(Poblacion poblacion){
        Poblacion mutaciones = new Poblacion(poblacion.getCromosomas().length);
        for(int i = 0; i < Cromosomas_Elite; i++){
            mutaciones.getCromosomas()[i] = poblacion.getCromosomas()[i];
        }
         for(int i = Cromosomas_Elite; i < poblacion.getCromosomas().length; i++){
            mutaciones.getCromosomas()[i] = mutarCromosoma(poblacion.getCromosomas()[i]);
        }    
        return mutaciones;
    }
    //Se cruzan los cromosomas segun sus genes.
    private Cromosoma crucescromosomas(Cromosoma cromosoma1, Cromosoma cromosoma2){
        Cromosoma crucescromosomas = new Cromosoma(Cromosoma_Fitness.length);
        for(int i = 0; i < Cromosomas_Elite; i++){
            if(Math.random() < 0.5){
                crucescromosomas.getGenes()[i] = cromosoma1.getGenes()[i];
            }else
            crucescromosomas.getGenes()[i] = cromosoma2.getGenes()[i];
        }          
        return crucescromosomas;
    }
    //Se mutan los cromosomas
    private Cromosoma mutarCromosoma(Cromosoma cromosoma){
        Cromosoma mutarcromosomas = new Cromosoma(Cromosoma_Fitness.length);
        for (int i = 0; i < cromosoma.getGenes().length; i++){
            if(Math.random() < frecuencia_Mutaciones){
                if(Math.random() < 0.5){
                mutarcromosomas.getGenes()[i] = 1;
                }else mutarcromosomas.getGenes()[i] = 0;
            }else mutarcromosomas.getGenes()[i] = cromosoma.getGenes()[i];
     
        }
        return mutarcromosomas;
    }
    //Metodo para seleccionar los cromosomas a traves de torneo, se seleccionan
    //un numero determinado de cromosomas, definido por la variable del inicio.
    //y luego se comparan con el cromosoma mas fuerte para definir si se pueden
    //cruzar.
    private Poblacion seleccionarCromosomasTournament(Poblacion poblacion){
        Poblacion torneo = new Poblacion(Tournament);
        for(int i = 0; i < Tournament; i++){
            torneo.getCromosomas()[i] = poblacion.getCromosomas()[(int)(Math.random()*poblacion.getCromosomas().length)];
        }
        torneo.ordenarCromosomasFitness();
        return torneo;
    }
}
