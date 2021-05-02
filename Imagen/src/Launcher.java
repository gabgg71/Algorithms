
import controlador.Controlador;
import java.awt.Color;
import java.util.Arrays;
import logica.AlgoritmoG;
import logica.Logica;
import logica.Poblacion;
import ventana.Ventana;


public class Launcher {
    public static void main(String[] args) {
        Logica log = new Logica();
        Controlador c = new Controlador();
        c.setLogica(log);
        Ventana ex = new Ventana(log, c);
        ex.getContentPane().setBackground(new Color(58,120,200));
        log.setVentana(ex);
        ex.setVisible(true);
        ex.setAlwaysOnTop(true);
        
        //Importante para que el algoritmo funcione:
        Poblacion poblacion = new Poblacion(AlgoritmoG.tamaño_poblacion).inicializarPoblacion();
        AlgoritmoG algoritmoG = new AlgoritmoG();
      
        //Seccion se puede eliminar una vez se pueda integrar el algoritmo a visual ------------------------------
        System.out.println("----------------------");
        System.out.println("Generacion # 0 " + " | Cromosoma fuerte: " +poblacion.getCromosomas()[0].getFitness());
        imprimirPoblacion(poblacion,"Cromosoma Objetivo: "+Arrays.toString(AlgoritmoG.Cromosoma_Fitness));
        //--------------------------------------------------------------------------------------------------------
        
        //Esta seccion hace que el algoritmo funcione y no se detenga hasta que el algoritmo termine
        //Sin embargo se pueden eliminar los prints una ves este funcione.
        int generacion = 0;
        while(poblacion.getCromosomas()[0].getFitness() < AlgoritmoG.Cromosoma_Fitness.length){
                generacion++;
                System.out.println("\n--------------");
                poblacion = algoritmoG.evolucion(poblacion);
                poblacion.ordenarCromosomasFitness();
                        System.out.println("Generacion "+generacion+ " | Cromosoma fuerte: " +poblacion.getCromosomas()[0].getFitness());
                        imprimirPoblacion(poblacion,"Cromosoma Objetivo: "+Arrays.toString(AlgoritmoG.Cromosoma_Fitness));
        }
    }
    //Metodo que se puede eliminar una vez se integra a visual
    //Ya que lo unico que hace es mostrar la poblacion actual.
    public static void imprimirPoblacion(Poblacion poblacion, String cabezera){
        System.out.println(cabezera);
        System.out.println("--------------------");
        for(int i = 0; i < poblacion.getCromosomas().length; i++){
            System.out.println("Cromosoma # "+ i +" : "+ Arrays.toString(poblacion.getCromosomas()[i].getGenes())+
                    " | Fitness: "+poblacion.getCromosomas()[i].getFitness());
        }
    }
}

