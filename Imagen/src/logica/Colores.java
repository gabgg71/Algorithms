
package logica;

import java.util.ArrayList;

public class Colores {
    public ArrayList<Integer> generatedRed = new ArrayList(), generatedGreen = new ArrayList(), generatedBlue = new ArrayList();

    public Colores(ArrayList<Integer> generatedRed,ArrayList<Integer> generatedGreen,ArrayList<Integer> generatedBlue) {
        
        this.generatedBlue = generatedBlue;
        this.generatedGreen = generatedGreen;
        this.generatedRed = generatedRed;
            
    }

    public ArrayList<Integer> getGeneratedRed() {
        return generatedRed;
    }

    public void setGeneratedRed(ArrayList<Integer> generatedRed) {
        this.generatedRed = generatedRed;
    }

    public ArrayList<Integer> getGeneratedGreen() {
        return generatedGreen;
    }

    public void setGeneratedGreen(ArrayList<Integer> generatedGreen) {
        this.generatedGreen = generatedGreen;
    }

    public ArrayList<Integer> getGeneratedBlue() {
        return generatedBlue;
    }

    public void setGeneratedBlue(ArrayList<Integer> generatedBlue) {
        this.generatedBlue = generatedBlue;
    }
    
    
}
