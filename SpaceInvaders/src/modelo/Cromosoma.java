
package modelo;

public class Cromosoma {
    String secuencia; 
    float fitness;

    public Cromosoma(String secuencia, float fitness) {
        this.secuencia = secuencia;
        this.fitness = fitness;
    }

    public String getSecuencia() {
        return secuencia;
    }

    public void setSecuencia(String secuencia) {
        this.secuencia = secuencia;
    }

    public float getFitness() {
        return fitness;
    }

    public void setFitness(float fitness) {
        this.fitness = fitness;
    }
    
    
}
