
package modelo;


public class Persona {
    int posTapabocas;
    int x;
    int y;
    int posLavarManos;
    int posVentilacion;
    double tiempo;
    int edad;
    int estado;
    int diasE = 0;
    int tipo;
    boolean cercaA = false;

    
    public Persona(int posTapabocas, int x, int y, int posLavarManos, int posVentilacion, int edad, int estado) {
        this.posTapabocas = posTapabocas;
        this.x = x;
        this.y = y;
        this.posLavarManos = posLavarManos;
        this.posVentilacion = posVentilacion;
        this.edad = edad;
        this.estado = estado;
    }



    public int getPosTapabocas() {
        return posTapabocas;
    }

    public void setPosTapabocas(int posTapabocas) {
        this.posTapabocas = posTapabocas;
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }


    public int getPosLavarManos() {
        return posLavarManos;
    }

    public void setPosLavarManos(int posLavarManos) {
        this.posLavarManos = posLavarManos;
    }

    public int getPosVentilacion() {
        return posVentilacion;
    }

    public void setPosVentilacion(int posVentilacion) {
        this.posVentilacion = posVentilacion;
    }

    public double getTiempo() {
        return tiempo;
    }

    public void setTiempo(double tiempo) {
        this.tiempo = tiempo;
    }

    public int getEdad() {
        return edad;
    }

    public void setEdad(int edad) {
        this.edad = edad;
    }


    public int getEstado() {
        return estado;
    }

    public void setEstado(int estado) {
        this.estado = estado;
    }

    public int getDiasE() {
        return diasE;
    }

    public void setDiasE(int diasE) {
        this.diasE = diasE;
    }

    public int getTipo() {
        return tipo;
    }

    public void setTipo(int tipo) {
        this.tipo = tipo;
    }

    public boolean isCercaA() {
        return cercaA;
    }

    public void setCercaA(boolean cercaA) {
        this.cercaA = cercaA;
    }
    
    

    @Override
    public String toString() {
        return "Se disponen de los siguiente datos de esta persona: " + 
                "\nuso de tapabocas: " + posTapabocas + "\n, lavado de manos: "
                + posLavarManos + "\n, ventilacion en los lugares que habita: " +
                posVentilacion + "\n, tiempo promedio de salida por evento: " 
                + tiempo + "\n, edad: " + edad +", ha estado cerca de un contagiado"
                + ": "+cercaA+ "\n, estado en relacion al covid: " +
                estado;
    }

    
    
    
    
    
 
}
