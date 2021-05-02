package modelo;

import java.awt.Color;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import javax.swing.JTextField;
import vista.Ventana;

public class Logica {
    double probsAcumulado;
    private ArrayList<Persona> persons = new ArrayList();
    public ArrayList<Integer> muertes = new ArrayList();
    public Ventana v;
    JTextField iteraciones;
    int v1;

    public void setVentana(Ventana v) {
        this.v = v;
    }

    public ArrayList<Persona> inicializa() {
        int personas = 20;
        String numberP = v.algo.getText().trim();
        if (!numberP.equals("")) {
            personas = Integer.parseInt(numberP);
        }
        int contador = 0;
        while (contador != personas) { //establecemos la posicion limite
            int ancho = v.d.getSize().width;
            int alto = v.d.getSize().height;
            int tapabocas = (int) (Math.random() * (2));
            int posX = (int) (Math.random() * (ancho));
            int posY = (int) (Math.random() * (alto));
            int lavado = (int) (Math.random() * (2));
            int ventilacion = (int) (Math.random() * (2));
            int edad = (int) (Math.random() * (90)+10);
            double estado = round((double) (Math.random() * (1)));
            if (estado > 0.7) {
                persons.add(new Persona(tapabocas, posX, posY, lavado, ventilacion, edad, 1));
            } else {
                persons.add(new Persona(tapabocas, posX, posY, lavado, ventilacion, edad, 0));
            }
            contador++;
        }
        return persons;
    }

    public void llamarTodo(ArrayList<Persona> p) { 
        for (int j = 0; j < persons.size(); j++) {
            this.probsAcumulado = 0;
            if (persons.get(j).getEstado() == 0) {
                deTapabocas(j);
                lavaManos(j);
                deEdad(j);
                calculaDistancias(j);
                if (this.probsAcumulado > 0.5) {
                    persons.get(j).setEstado(1);
                }
                //System.out.println(this.probsAcumulado);
            } else {  //Cada iteracion es un dia 
                if (persons.get(j).getDiasE() == 15) {  //Si lleva 15 dias ya se cura
                    persons.get(j).setEstado(0);
                    persons.get(j).setDiasE(0);
                } else {
                    persons.get(j).setDiasE(persons.get(j).getDiasE() + 1);
                }
            }
        }
        moverPuntos();
        mortalidad();
        persons = p;
        v.d.person = getLista();
        v.d.repaint();
        v1 = Integer.parseInt(v.iteraciones.getText().trim()) + 1;
        v.iteraciones.setText("" + v1 + "");
    }

    public void deTapabocas(int i) {  //Probabilidades tapabocas y ventilacion
        if (persons.get(i).getPosTapabocas() == 1 && persons.get(i).getPosVentilacion() == 1) { //Uso tapabocas y en los lugares que frecunta hay ventilacion
            if (probsAcumulado > 0.4) {
                probsAcumulado -= round(0.4);
            } else {
                probsAcumulado = round(0);
            }
        } else if (persons.get(i).getPosTapabocas() == 1 && persons.get(i).getPosVentilacion() == 0) {  //no uso
            probsAcumulado += round(0.3);
        } else if (persons.get(i).getPosTapabocas() == 0 && persons.get(i).getPosVentilacion() == 1) {  //no uso
            probsAcumulado += round(0.4);
        } else {  //No tapabocas no ventilacion 
            probsAcumulado += round(0.5);
        }
    }

//Lavar las manos disminuye la probabilidad un 36% segun Unicef
    public void lavaManos(int i) {
        if (persons.get(i).getPosLavarManos() == 1) {
            if (probsAcumulado >= 0.36) {
                probsAcumulado -= round(0.36);
            } else {
                probsAcumulado = round(0);
            }
        }
    }

    public ArrayList<Persona> getLista() {
        return persons;
    }

    public void deEdad(int i) {
        double promTiempoFuera;
        int edad = persons.get(i).getEdad();
        if (edad > 0 && edad < 20) {
            persons.get(i).setTipo(1);
            promTiempoFuera = round((double) (Math.random() * (6))+0.2);
        } else if (edad >= 20 && edad <= 40) {
            persons.get(i).setTipo(2);
            promTiempoFuera = round((double) (Math.random() * (8))+0.2);
        } else if (edad >= 40 && edad <= 60) {
            persons.get(i).setTipo(3);
            promTiempoFuera = round((double) (Math.random() * (5))+0.2);
        } else {
            persons.get(i).setTipo(4);
            promTiempoFuera = round((double) (Math.random() * (3))+0.2);
        }
        persons.get(i).setTiempo(promTiempoFuera);
    }

    /*Probabilidad de contagio de acuerdo a la distancia y el tiempo*/
    public void calculaDistancias(int i) {
        int ancho = v.d.getSize().width;
        int alto = v.d.getSize().height;
        double distanciaMax = Math.sqrt((ancho * ancho) + (alto * alto));
        for (int j = 0; j < persons.size() - 1; j++) {
            if (i != j && persons.get(i).getEstado() == 0) {
                double distancia = Math.sqrt((persons.get(i).getX() - persons.get(j).getX() * (persons.get(i).getX() - persons.get(j).getX()))
                        + (persons.get(i).getY() - persons.get(j).getY()) * (persons.get(i).getY() - persons.get(j).getY()));
                if (distancia < distanciaMax / 4) {
                    if (distancia >= distanciaMax / 8 && persons.get(i).getTiempo() > 4 && persons.get(j).getEstado() == 1) {
                        probsAcumulado += round(0.2); 
                        break;
                    } else if (distancia >= distanciaMax / 8 && persons.get(i).getTiempo() < 4 && persons.get(j).getEstado() == 1) {
                        probsAcumulado += round(0.1);
                        break;
                    } else if (distancia < distanciaMax / 8 && persons.get(i).getTiempo() > 4 && persons.get(j).getEstado() == 1) {
                        probsAcumulado += round(0.49);
                        persons.get(i).setCercaA(true);
                        break;
                    } else if (distancia < distanciaMax / 8 && persons.get(i).getTiempo() < 4 && persons.get(j).getEstado() == 1) {
                        probsAcumulado += round(0.3);
                        persons.get(i).setCercaA(true);
                        break;
                    }
                }
            }

        }

    }

    public void moverPuntos() {
        int ancho = v.d.getSize().width;
        int alto = v.d.getSize().height;
        double probMovimiento;
        for (int i = 0; i < persons.size(); i++) {
            probMovimiento = round((double) (Math.random() * (1)));
            double[] arr = {0.4, 0.5, 0.7, 0.9};
            for (int j = 0; j < arr.length; j++) {
                if (persons.get(i).getTipo() == j + 1) { //tipos
                    if (probMovimiento > arr[j]) {
                        int posX = (int) (Math.random() * (ancho));
                        int posY = (int) (Math.random() * (alto));
                        persons.get(i).setX(posX);
                        persons.get(i).setY(posY);
                    }
                }
            }
        }
    }

    public static double round(double n) {
        BigDecimal instance = new BigDecimal(Double.toString(n));
        instance = instance.setScale(2, RoundingMode.HALF_UP);
        return instance.doubleValue();
    }

    public void mortalidad() {
        int c=0;
        for (int i = 0; i < persons.size(); i++) {
            float probMuerte = (float) (Math.random() * (0.40));
            double[] arr = {0.002, 0.002, 0.01, 0.12};
            for (int j = 0; j < arr.length; j++) {
                if (persons.get(i).getTipo() == (j + 1)) {
                    if (probMuerte <= arr[j]) {
                        v.notificaMuerte.setText(v.notificaMuerte.getText()+"Murio persona con registro n: "+(persons.indexOf(persons.get(i))+1)+", tenia "+persons.get(i).getEdad()+" años.\n");
                        persons.remove(i);
                        c+=1;
                    }
                }
            }
        }
        muertes.add(c);
    }
    
    /*dado que al poner el mas lo mas cerca punto sup izquierdo dicha coordenada
    no es 0 0 si no 11 38, parece ser que una parte se la reserva el marco, debemos tener
    en cuenta este corrimiento*/
    public String dameElDato(int x, int y){
        int c=0;
        for (Persona p : persons) {
            c+=1;
            if((p.getX() >= (x-60) && p.getX() <= (x+60)) && (p.getY() >= (y-60) && p.getY() <= (y+60))){
                System.out.println("Sirvio");
                System.out.println(p.getX());
                System.out.println(p.getY());
                if(p.getEstado()==1){
                    return p.toString()+"\nregistro numero: "+c+"\n,dias que ha estado enfermo: "+p.getDiasE();
                }else{
                    return p.toString()+"\nregistro numero: "+c;
                } 
            }
        }
        return "No se dispone del registro";
    }
    
}
