package logica;

public class Cromosoma {
    private boolean fitnessCambiado = true;
    private int fitness = 0;
    private int [] genes;
    public Cromosoma(int lenght){
        genes = new int[lenght];
    }
    
    public Cromosoma inicializarCromosoma(){
        //Aqui se inicializan los cromosomas
        //Se DEBE cambiar una vez se establezcan los pixeles por estos
        //Ya que ahora se generan valores aleatorios.
        for(int i = 0; i < genes.length; i++){
            if(Math.random() >= 0.5){
                genes[i] = 1;
                
            }else genes[i] = 0;
            
        }
        return this;
    }

    public int[] getGenes() {
        fitnessCambiado = true;
        return genes;
    }

    public int getFitness() {
        if(fitnessCambiado){
            fitness = recalcularFitness();
            fitnessCambiado = false;
        }
        return fitness;
    }
    
    public int recalcularFitness(){
        int cromosomaFitness = 0;
        for(int i = 0; i < genes.length; i++){
            if(genes[i] == AlgoritmoG.Cromosoma_Fitness[i]){
                cromosomaFitness++;
            }
        }
        return cromosomaFitness;
    }
    
    @Override
    public String toString() {
        return "Cromosoma{" + "genes=" + genes + '}';
    }
   
    
}
