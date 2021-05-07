package logica;

import java.util.Comparator;

public class Necesario {
    int distancia;
    int posicion;

    public Necesario(int distancia, int posicion) {
        this.distancia = distancia;
        this.posicion = posicion;
    }
    
    

    public int getDistancia() {
        return distancia;
    }

    public void setDistancia(int distancia) {
        this.distancia = distancia;
    }

    public int getPosicion() {
        return posicion;
    }

    public void setPosicion(int posicion) {
        this.posicion = posicion;
    }
      
    public static Comparator<Necesario> ferom = new Comparator<Necesario>() {         
    @Override         
    public int compare(Necesario jc1, Necesario jc2) {             
      return (jc2.getDistancia() < jc1.getDistancia() ? -1 :                     
              (jc2.getDistancia() == jc1.getDistancia() ? 0 : 1));           
    }     
  }; 
    
}
